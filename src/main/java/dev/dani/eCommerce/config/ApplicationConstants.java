package dev.dani.eCommerce.config;

public enum ApplicationConstants {

    EMPTY_STRING(""),
    PAGE_TITLE("pageTitle"),
    APPLICATION_NAME("eCommerce"),
    LOGIN("Log In"),
    SIGNUP("Sign Up"),
    PROFILE("Profile"),
    EDIT_PROFILE("Edit profile"),
    ADMIN("Admin page"),
    USER_LIST("Users"),
    SINGLE_USER("Single user"),
    CREATE_NEW_ADMIN("Create new admin"),
    REQUEST("request"),
    USER("user"),
    FAILED("failed"),
    NEW_PRODUCT("New product");


    private final String value;

    ApplicationConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
