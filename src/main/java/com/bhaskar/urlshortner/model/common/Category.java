package com.bhaskar.urlshortner.model.common;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public enum Category {
    CLOTHS("cloths"),
    FURNITURE("furniture"),
    MOBILE("mobile"),
    LAPTOP("laptop"),
    SHOE("shoe"),
    ACCESSORIES("accessories"),
    INVALID_CATEGORY("invalid category");
    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String value() {
        return name;
    }

    @JsonCreator
    public static Category find(String value) {
        return Arrays.stream(values())
                .filter(category -> StringUtils.equalsIgnoreCase(category.name(), value) || StringUtils.equalsIgnoreCase(category.value(), value))
                .findFirst().orElse(Category.INVALID_CATEGORY);
    }

}
