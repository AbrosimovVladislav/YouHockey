package ru.yourhockey.model.product_attributes;

import java.util.Set;

public enum Age {
    UNDEFINED {
        @Override
        Set<String> textValues() {
            return Set.of();
        }
    },
    YTH  {
        Set<String> textValues = Set.of("young", "yth", "youth", "детск");
        @Override
        Set<String> textValues() {
            return textValues;
        }
    },
    JR  {
        Set<String> textValues = Set.of("jr", "junior", "юниор");
        @Override
        Set<String> textValues() {
            return textValues;
        }
    },
    INT  {
        Set<String> textValues = Set.of("int", "intermed", "intermediate");
        @Override
        Set<String> textValues() {
            return textValues;
        }
    },
    SR  {
        Set<String> textValues = Set.of("sr", "senior", "взросл");
        @Override
        Set<String> textValues() {
            return textValues;
        }
    };

    public static Age of(String text){
        var txt = text.toLowerCase();
        Age[] ages = values();
        for (Age age : ages) {
            for (String textValue : age.textValues()) {
                if (txt.contains(textValue)) return age;
            }
        }
        return UNDEFINED;
    }

    abstract Set<String> textValues();
}
