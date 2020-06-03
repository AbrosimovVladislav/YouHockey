package ru.yourhockey.repo.querybuilder;

import ru.yourhockey.model.product_attributes.Age;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;


public enum Operation {
    EQUALS {
        @Override
        public Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path) {
            if (paramName.equalsIgnoreCase("age")) return criteriaBuilder.equal(path, Age.of(qbParamValue));
            return criteriaBuilder.equal(path, qbParamValue);
        }
    },
    IN {
        @Override
        public Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path) {
            CriteriaBuilder.In inClause = criteriaBuilder.in(path);
            for (String value : qbParamValue.split(",")) {
                if (paramName.equalsIgnoreCase("age")) {
                    inClause.value(Age.of(value));
                } else if (paramName.endsWith("Id") || paramName.endsWith("id")) {
                    inClause.value(Long.parseLong(value));
                } else inClause.value(value);
            }
            return inClause;
        }
    },
    BETWEEN {
        @Override
        public Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path) {
            String[] interval = qbParamValue.split("interval");
            return criteriaBuilder.between(path, Double.valueOf(interval[0]), Double.valueOf(interval[1]));
        }
    };

    public abstract Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path);

    public static Operation define(String paramValue) {
        if (paramValue.contains(",")) return IN;
        else if (paramValue.contains("interval")) return BETWEEN;
        else return EQUALS;
    }
}
