package com.getir.readingisgood.log;

public enum LogTypeEnum {

    INFO(1L, "INFO"),
    WARNING(2L, "WARNING"),
    ERROR(3L, "ERROR");

    private Long id;
    private String value;

    LogTypeEnum(Long id, String value) {
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
