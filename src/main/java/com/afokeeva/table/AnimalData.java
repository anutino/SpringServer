package com.afokeeva.table;

//import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//@Entity
public class AnimalData {
    @JsonProperty("id")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int mId;

    @JsonProperty("age")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private float mAge;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String mName;

    @JsonProperty("description")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String mDescription;

    @JsonProperty("type")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int mType;

    @JsonProperty("mainPicture")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private String mMainPicture;

    @JsonProperty("mediaList")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<String> mMediaList = null;

    @JsonProperty("isFavorite")
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private boolean mIsFavorite;

    public AnimalData(int id, float age, String name, int type, String mainPicture) {
        mId = id;
        mAge = age;
        mName = name;
        mType = type;
        mMainPicture = mainPicture;
    }

//    public AnimalData(float age, String name, String description, int type) {
//        mAge = age;
//        mName = name;
//        mDescription = description;
//        mType = type;
//    }
//
//    public AnimalData(int id, float age, String name, String description, int type, String mainPicture) {
//        mId = id;
//        mAge = age;
//        mName = name;
//        mDescription = description;
//        mType = type;
//        mMainPicture = mainPicture;
//    }

    public AnimalData(int id, String description) {
        mId = id;
        mDescription = description;
    }

    public AnimalData(int id, float age, String name, String mainPicture) {
        mId = id;
        mAge = age;
        mName = name;
        mMainPicture = mainPicture;
    }

    public int getId() {
        return mId;
    }

    public float getAge() {
        return mAge;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public int getType() {
        return mType;
    }

    public void setIsFavorite(boolean isFavorite) {
        mIsFavorite = isFavorite;
    }

    public void setMediaList(ArrayList<String> mediaList) {
        mMediaList = mediaList;
    }

    public ArrayList<String> getMediaList() {
        return mMediaList;
    }

    public String getMainPicture() {
        return mMainPicture;
    }
}

