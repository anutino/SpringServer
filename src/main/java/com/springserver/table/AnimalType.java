package com.springserver.table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnimalType {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int mId;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String mName;

    @JsonProperty("img_url")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String mImgUrl;

    public AnimalType(int id, String name, String imgUrl) {
        mId = id;
        mName = name;
        mImgUrl = imgUrl;
    }
}
