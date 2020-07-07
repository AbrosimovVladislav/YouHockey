package ru.yourhockey.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import ru.yourhockey.web.dto.ProductDto;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Проверить работу старого и нового QB
 * https://docs.google.com/spreadsheets/d/1CUveJP_2wrWWrHqKbnBZsJHCOc-LGdwvf6Ui3ByP1aI/
 * EXCEL -> CSV -> read class
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
class ProductApiControllerTest {
    private static MockMvc staticMockMvc;
    private static JdbcTemplate staticJdbcTemplate;

    private static List<InputTestData> inputTestData;
    public static final String PRODUCT_URL_ROOT = "http://localhost:8082/products?";

    private static final String[] HEADERS = {"group", "offerPrice", "brandShortName", "offerInStock", "typeTypeId",
            "age", "sort", "page", "size", "expected;"};

    @BeforeAll
    public static void init(@Autowired DataSource dataSource, @Autowired JdbcTemplate jdbcTemplate, @Autowired MockMvc mockMvc) throws IOException, InterruptedException, SQLException {
        staticJdbcTemplate = jdbcTemplate;
        staticMockMvc = mockMvc;
        downloadCsvFile();
        try (Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, new ClassPathResource("/test.sql"));
        }

        inputTestData = readInputDataFromCSV("auto-test-res/test-data.csv");
    }

    private static void downloadCsvFile() throws IOException, InterruptedException {
        String csvUrl = "https://docs.google.com/spreadsheets/d/1CUveJP_2wrWWrHqKbnBZsJHCOc-LGdwvf6Ui3ByP1aI/gviz/tq?tqx=out:csv&sheet=Лист1";
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create(csvUrl)).header("Accept", "text/csv").build();
        log.info(String.valueOf(httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofFile(Path.of("auto-test-res/test-data.csv")))));
    }

    @ParameterizedTest
    @MethodSource("provideData")
    public void runTest(String expected, String actual, String url, String sql) {
        log.info("Test for {} and {} started", expected, actual);
        log.info("Url (actual): {}", url);
        log.info("Sql (expected): {}", sql);
        assertEquals(expected, actual);
    }

    private static List<Arguments> provideData() throws Exception {
        List<Arguments> arguments = new ArrayList<>();
        for (var itd : inputTestData) {
            String url = createUrlFromItd(itd);
            arguments.add(Arguments.of(
                    createExpectedIdsFromItd(itd, staticJdbcTemplate),
                    getActualIds(url),
                    url,
                    itd.getSql()));
        }
        return arguments;
    }

    private static String getActualIds(String url) throws Exception {
        ResultActions result = null;
        try {
            result = staticMockMvc.perform(get(url))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            return "Error";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductDto> productDtos = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), new TypeReference<>() {
        });

        return productDtos.stream()
                .map(pd -> pd.getProductId().toString())
                .collect(Collectors.joining(","));
    }

    private static String createUrlFromItd(InputTestData itd) {
        String url = PRODUCT_URL_ROOT;
        if (!itd.getOfferPrice().equals("")) {
            url += "offer.price=" + itd.getOfferPrice();
        }
        if (!itd.getBrandShortName().equals("")) {
            url += "&brand.shortName=" + itd.getBrandShortName();
        }
        if (!itd.getOfferInStock().equals("")) {
            url += "&offer.inStock=" + itd.getOfferInStock();
        }
        if (!itd.getTypeTypeId().equals("")) {
            url += "&type.typeId=" + itd.getTypeTypeId();
        }
        if (!itd.getAge().equals("")) {
            url += "&age=" + itd.getAge();
        }
        if (!itd.getSort().equals("")) {
            url += "&sort=" + itd.getSort();
        }
        if (!itd.getPage().equals("")) {
            url += "&page=" + itd.getPage();
        }
        if (!itd.getSize().equals("")) {
            url += "&size=" + itd.getSize();
        }
        return url;
    }

    private static String createExpectedIdsFromItd(InputTestData itd, JdbcTemplate jdbcTemplate) {
        log.info("Query for sql: {} started", itd.getSql());
        if (itd.expected.equals("blank")) return "";
        if (!itd.expected.equals("")) {
            return itd.expected;
        }
        try {
            return String.join(",", jdbcTemplate.query(itd.getSql(), new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    return resultSet.getString("product_id");
                }
            }));
        } catch (Exception e) {
            log.error("Problem with sql query execution : {}", e.getMessage());
            return "Error";
        }
    }

    @Data
    @Accessors(chain = true)
    public static class InputTestData {
        private String group;
        private String offerPrice;
        private String brandShortName;
        private String offerInStock;
        private String typeTypeId;
        private String age;
        private String sort;
        private String page;
        private String size;
        private String expected;
        private String sql;

        InputTestData buildSql() {
            String sql = String.format(
                    "select p.product_id " +
                            "from product as p " +
                            "inner join offer as o on p.product_id=o.product_id " +
                            "inner join brand b on p.brand_id = b.brand_id " +
                            "inner join rating r on p.rating_id = r.rating_id " +
                            "where b.short_name in (%s) and " +
                            "p.type_id in (%s) and " +
                            "p.age in (%s) and " +
                            "o.in_stock=%s " +
                            "%s" +
                            "group by p.product_id, %s " +
                            "order by %s " +
                            "limit %s offset %s;",
                    formatBrandShortName(brandShortName),
                    formatTypeId(typeTypeId),
                    formatAge(age),
                    formatInStock(offerInStock),
                    formatPrice(offerPrice),
                    formatGroupBy(sort),
                    formatOrderBy(sort),
                    formatLimit(size), formatOffset(page, size)
            );
            return this.setSql(sql);
        }

        private String formatOffset(String page, String size) {
            return String.valueOf(Integer.parseInt(page) * Integer.parseInt(size));
        }

        private String formatLimit(String size) {
            return size;
        }

        private String formatOrderBy(String sort) {
            if (sort.equals("")) {
                return "r.value desc";
            }
            return sort
                    .replace(',', ' ')
                    .replace("minPrice", "min_price")
                    .replace("reviewCount", "review_count")
                    .replace("rating.value", "r.value");
        }

        private String formatGroupBy(String sort) {
            if (sort.equals("")) {
                return "r.value";
            }
            return sort.split(",")[0]
                    .replace("minPrice", "min_price")
                    .replace("reviewCount", "review_count")
                    .replace("rating.value", "r.value");
        }

        private String formatPrice(String offerPrice) {
            if (StringUtils.isBlank(offerPrice)) return "";
            String[] prices = offerPrice.split("interval");
            var minPrice = prices[0];
            var maxPrice = prices[0];
            if (prices.length > 1) {
                maxPrice = prices[1];
            }
            return "and o.price between " + minPrice + " and " + maxPrice;
        }

        private String formatInStock(String offerInStock) {
            if ("true".equals(offerInStock))
                return "true";
            else if ("false".equals(offerInStock))
                return "o.in_stock";
            else if (StringUtils.isBlank(offerInStock))
                return "o.in_stock";
            else return "wtf";
        }

        private String formatAge(String age) {
            if (StringUtils.isBlank(age))
                return "p.age";
            else return Stream.of(age.split(","))
                    .map(b -> "'" + b + "'")
                    .collect(Collectors.joining(",")) + ",'UNDEFINED'";
        }

        private String formatTypeId(String typeTypeId) {
            if (StringUtils.isBlank(typeTypeId))
                return "p.type_id";
            else return typeTypeId;
        }

        private String formatBrandShortName(String brandShortName) {
            if (StringUtils.isBlank(brandShortName))
                return "b.short_name";
            else return Stream.of(brandShortName.split(","))
                    .map(b -> "'" + b + "'")
                    .collect(Collectors.joining(","));
        }
    }

    private static List<InputTestData> readInputDataFromCSV(String filePath) throws IOException {
        Reader in = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withHeader(HEADERS)
                .withFirstRecordAsHeader()
                .parse(in);
        return StreamSupport.stream(records.spliterator(), false)
                .map(rec -> new InputTestData()
                        .setGroup(rec.get("Group"))
                        .setOfferPrice(rec.get("offerPrice"))
                        .setBrandShortName(rec.get("brandShortName"))
                        .setOfferInStock(rec.get("offerInStock"))
                        .setTypeTypeId(rec.get("typeTypeId"))
                        .setAge(rec.get("age"))
                        .setSort(rec.get("sort"))
                        .setPage(rec.get("page"))
                        .setSize(rec.get("size"))
                        .setExpected(rec.get("expected"))
                        .buildSql()
                ).collect(Collectors.toList());
    }
}
