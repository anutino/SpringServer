package com.springserver.repository;

import com.springserver.table.AnimalData;
import com.springserver.table.AnimalType;

import java.util.ArrayList;

public interface AnimalRepository {

        ArrayList<AnimalType> getAnimalTypes();
        ArrayList<AnimalData> getAnimalShortInfoList();
        AnimalData getAnimalDetailedInfo(String animalId);

        ArrayList<AnimalData> getAnimalShortInfoListFilteredByAge(String from, String to);
        ArrayList<AnimalData> getAnimalShortInfoListFilteredByType(String type);
        ArrayList<AnimalData> getAnimalShortInfoListFilteredByAgeAndType(String from, String to, String type);

        ArrayList<AnimalData> getFavoriteAnimalList(String userId);
        boolean addFavoriteAnimal(String userId, String animalId);
        boolean deleteFavoriteAnimal(String userId, String animalId);
}
