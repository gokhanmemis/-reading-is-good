package com.getir.readingisgood.util;

public enum MessageListEnum {

    ERROR_AUTH_BAD_URL("İstek atılan URL bilgisi hatalı."),
    ERROR_AUTH_INVALID_TOKEN("Geçersiz token: {0}"),

    INFO_CONFIGURATION_SUCCESSFULLY_LISTED("Konfigürasyon bilgisi başarıyla listelendi."),
    INFO_CONFIGURATION_SUCCESSFULLY_UPDATED("Konfigürasyon bilgisi başarıyla güncellendi."),
    WARNING_CONFIGURATION_EMPTY_VARIABLE("Konfigürasyon bilgisi eksik."),

    INFO_CUSTOMER_LISTED("Customers' information successfully are listed."),
    INFO_CUSTOMER_SAVED("Customer information successfully is saved."),

    INFO_BOOK_LISTED("Books' information successfully are listed."),
    INFO_BOOK_SAVED("Book information successfully is saved."),

    INFO_ORDER_LISTED("Orders' information are successfully listed."),
    INFO_ORDER_SAVED("Orders' information is successfully saved."),
    INFO_ORDER_GET_BY_ID("Order information has been successfully retrieved."),
    WARNING_ORDER_EMPTY_OBJECT("Order information is empty."),
    WARNING_ORDER_BOOK_EMPTY_OBJECT("Order book information is empty."),
    WARNING_ORDER_BOOK_WRONG_OBJECT("Order book information is wrong."),
    WARNING_ORDER_CUSTOMER_EMPTY_OBJECT("Order customer information is empty."),
    WARNING_ORDER_CUSTOMER_WRONG_OBJECT("Order customer information is wrong."),
    WARNING_ORDER_QUANTITY_EMPTY_OBJECT("Order quantity is empty."),
    WARNING_ORDER_DATE_EMPTY_OBJECT("Order date is empty."),
    WARNING_ORDER_NOT_ENOUGH_BOOK("There are not enough books in stock."),

    INFO_STATISTIC_LISTED("Statistics are successfully listed."),

    ;

    private String value;

    MessageListEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
