package com.getir.readingisgood.log;

public enum ApplicationTypeEnum {

    GETIR_MAIN_API(1L, "GETIR_MAIN_API");

    private Long id;
    private String value;

    ApplicationTypeEnum(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
