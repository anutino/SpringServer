package com.springserver.repository;


import com.springserver.database.ExecuteQueries;
import com.springserver.table.AnimalData;
import com.springserver.database.Query;

//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import com.springserver.table.AnimalType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class Animal implements AnimalRepository {
    private static final Logger log = LogManager.getLogger(Animal.class);

    private Query mQuery = new Query();

    @Override
    public ArrayList<AnimalType> getAnimalTypes() {
        ArrayList<AnimalType> animalList = ExecuteQueries.getAnimalTypes(mQuery.getQueryAnimalTypes());
        return animalList;
    }

    @Override
    public ArrayList<AnimalData> getAnimalShortInfoList() {
        ArrayList<AnimalData> animalList = ExecuteQueries.getAnimalList(mQuery.getQueryAnimalsShortInfo());
        return animalList;
    }

    @Override
    public AnimalData getAnimalDetailedInfo(String animalId) {
        AnimalData animal = ExecuteQueries.getAnimalDescription(mQuery.getQueryDescriptionById(animalId));
        ArrayList<String> mediaList = ExecuteQueries.getMediaById(mQuery.getQueryMediaById(animalId));
        animal.setMediaList(mediaList);
        return animal;
    }

    public ArrayList<AnimalData> getAnimalShortInfoListFilteredByAge(String from, String to) {
        return ExecuteQueries.getAnimalList(mQuery.getQueryAnimalShortInfoListFilteredByAge(from, to));
    }

    public ArrayList<AnimalData> getAnimalShortInfoListFilteredByType(String type) {
        return ExecuteQueries.getAnimalListFilteredByType(mQuery.getQueryAnimalShortInfoListFilteredByType(type));
    }


    public ArrayList<AnimalData> getAnimalShortInfoListFilteredByAgeAndType(String from, String to, String type) {
        return ExecuteQueries.getAnimalList(mQuery.
                getQueryAnimalShortInfoListFilteredByAgeAndType(from, to, type));
    }

    @Override
    public boolean addFavoriteAnimal(String userId, String animalId) {
        return ExecuteQueries.executeQuery(mQuery.getQueryInsertFavoriteAnimal(userId, animalId));
    }

    @Override
    public boolean deleteFavoriteAnimal(String userId, String animalId) {
        return ExecuteQueries.executeQuery(mQuery.getQueryDeleteFavoriteAnimal(userId, animalId));
    }

    @Override
    public ArrayList<AnimalData> getFavoriteAnimalList(String userId) {
        return ExecuteQueries.getAnimalList(mQuery.getQueryFavoriteAnimalByUserId(userId));
    }
}
