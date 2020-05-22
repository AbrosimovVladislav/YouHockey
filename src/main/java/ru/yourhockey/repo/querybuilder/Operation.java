package ru.yourhockey.repo.querybuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;


public enum Operation {
    EQUALS {
        @Override
        public Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path) {
            return criteriaBuilder.equal(path, qbParamValue);
        }
    },
    IN {
        @Override
        public Predicate getPredicate(String paramName, String qbParamValue, CriteriaBuilder criteriaBuilder, Path path) {
            CriteriaBuilder.In inClause = criteriaBuilder.in(path);
            for (String value : qbParamValue.split(",")) {
                inClause.value(paramName.endsWith("Id") || paramName.endsWith("id") ? Long.parseLong(value) : value);
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
