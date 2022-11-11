package com.springserver.database;

import com.springserver.table.UserData;

public class Query {

    public String getQueryAnimalsShortInfo() {
        return "SELECT animal.id_animal,"
                + "animal.age,"
                + "animal.name,"
                + "animal.id_type,"
                + "image.img_url"
                + " FROM animal "
                + " LEFT JOIN image ON image.id_image = animal.id_main_picture";
    }

    public String getQueryAnimalTypes() {
        return "SELECT * FROM animals.type";
    }

    public String getQueryDescriptionById(String animalId) {
        return "SELECT id_animal,"
                + "description"
                + " FROM animal"
                + " WHERE id_animal = " + animalId;
    }

    public String getQueryMediaById(String animalId) {
        return "SELECT img_url"
                + " FROM image"
                + " WHERE id_animal = " + animalId;
    }

    public String getQueryAnimalShortInfoListFilteredByAge(String from, String to) {
        return "SELECT  animal.id_animal, animal.age,"
                + "animal.name,"
                + "animal.id_type,"
                + "image.img_url"
                + " FROM animal"
                + " INNER JOIN image ON animal.id_main_picture=image.id_image"
                + " WHERE animal.age BETWEEN " + from + " AND " + to + " ORDER BY animal.age";
    }

    public String getQueryAnimalShortInfoListFilteredByType(String type) {
        return "SELECT animal.id_animal, animal.age,"
                + "animal.name,"
                + "image.img_url"
                + " FROM animal"
                + " INNER JOIN image ON animal.id_main_picture=image.id_image"
                + " INNER JOIN type ON animal.id_type=type.id_type"
                + " WHERE type.id_type=" + type;
    }

    public String getQueryAnimalShortInfoListFilteredByAgeAndType(String from, String to, String type) {
        return "SELECT animal.id_animal,"
                + "animal.age,"
                + "animal.name,"
                + "animal.id_type,"
                + "image.img_url"
                + " FROM animal"
                + " INNER JOIN image ON animal.id_main_picture=image.id_image"
                + " INNER JOIN  type ON animal.id_type=type.id_type"
                + " WHERE type.id_type=" + type
                + " AND animal.age BETWEEN " + from + " AND " + to + " ORDER BY animal.id_animal";
    }

    public String getQueryInsertUser(UserData entity) {
        return "INSERT INTO animals.user (name, login, password) VALUES ("
                + "'" + entity.getName() + "',"
                + "'" + entity.getLogin() + "',"
                + "'" + entity.getPassword() + "')";
    }

    public String getQueryDeleteUser(String userId) {
        return "DELETE FROM animals.user WHERE id_user=" + userId;
    }

    public String getQuerySignInUser(String login, String password) {
        return "SELECT 1 FROM user WHERE login='" + login + "' and password='" + password + "' LIMIT 1";
    }

    public String getQueryInsertFavoriteAnimal(String userId, String animalId) {
        return "INSERT INTO animals.favorite (id_user, id_animal) VALUES ('" + userId + "', " + animalId + ")";
    }

    public String getQueryDeleteFavoriteAnimal(String userId, String animalId) {
        return "DELETE FROM animals.favorite WHERE id_user=" + userId + " AND id_animal=" + animalId + ")";
    }

    public String getQueryFavoriteAnimalByUserId(String userId) {
        return "SELECT animal.id_animal,"
                + "animal.age,"
                + "animal.name,"
                + "animal.id_type,"
                + "image.img_url"
                + " FROM animal"
                + " INNER JOIN image ON animal.id_main_picture=image.id_image"
                + " INNER JOIN favorite ON animal.id_animal=favorite.id_animal"
                + " WHERE favorite.id_user = " + userId;
    }

}
