package ru.yourhockey.web.preparer;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import ru.yourhockey.model.BasicEntity;
import ru.yourhockey.model.offer.Offer;
import ru.yourhockey.model.product.Product;
import ru.yourhockey.model.product_attributes.Review;

@Component
public class SortPreparer implements Preparer {
    @Override
    public void prepare(FilterAndPageable filterAndPageable, Class<? extends BasicEntity> entityClass) {
        Pageable pageable = filterAndPageable.getPageable();
        String sortParam = filterAndPageable.getFilter().remove("sort");
        if (sortParam == null) {
            filterAndPageable.setPageable(getDefaultSortingProperty(pageable, entityClass));
        } else {
            String sortProp = sortParam.substring(0, sortParam.indexOf(","));
            Sort sort = Sort.by(pageable.getSort().getOrderFor(sortProp));
            filterAndPageable.setPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));
        }
    }

    private static PageRequest getDefaultSortingProperty(Pageable pageable, Class<? extends BasicEntity> entityClass) {
        String sortingProperty = "";
        Sort.Direction dir = Sort.Direction.DESC;
        if (entityClass.isAssignableFrom(Product.class)) {
            // Temp solution WHILE rating not implemented
            // sortingProperty = Rating.RATING_VALUE_SORT;
            sortingProperty = Product.PRODUCT_MIN_PRICE;
        } else if (entityClass.isAssignableFrom(Offer.class)) {
            sortingProperty = Offer.OFFER_POPULARITY;
        } else if (entityClass.isAssignableFrom(Review.class)) {
            sortingProperty = Review.REVIEW_MARK;
        }
        return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(dir, sortingProperty));
    }
}
