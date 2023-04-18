package com.example.projectliboo.model.dtos;

public class UserSearchDto {

    private String searchType;
    private String value;

    public UserSearchDto() {
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
