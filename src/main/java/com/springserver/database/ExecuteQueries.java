package com.springserver.database;

import com.springserver.table.AnimalData;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;

import com.springserver.table.AnimalType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ExecuteQueries {

    private static final Logger LOG = LogManager.getLogger(ExecuteQueries.class);
    private static final int SUCCESS = 1;
    private static final String ID_ANIMAL = "id_animal";
    private static final String DESCRIPTION = "description";
    private static final String AGE = "age";
    private static final String ID_TYPE = "id_type";
    private static final String NAME = "name";
    private static final String IMG_URL = "img_url";
    private static final String ID_USER = "id_user";

    public static boolean executeQuery(String query) {
        Connection connection = new DBConnection().getConnection();
        try (Statement statement = connection.createStatement()) {
            return statement.executeUpdate(query) == SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static ArrayList<AnimalType> getAnimalTypes(String query) {
        ArrayList<AnimalType> list = new ArrayList();
        AnimalType type;
        LOG.info("getAnimalTypes " );
        Connection connection = DBConnection.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                type =  new AnimalType(resultSet.getInt(ID_TYPE), resultSet.getString(NAME), resultSet.getString(IMG_URL));
                list.add(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOG.info("getAnimalTypes list="+list );
        return list;
    }


    public static AnimalData getAnimalDescription(String query) {
        AnimalData animal = null;
        Connection connection = new DBConnection().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                animal = new AnimalData(resultSet.getInt(ID_ANIMAL),
                        resultSet.getString(DESCRIPTION));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return animal;
    }


    public static ArrayList<AnimalData> getAnimalListFilteredByType(String query) {
        ArrayList<AnimalData> list = new ArrayList();
        AnimalData animal;
        Connection connection = new DBConnection().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                animal = new AnimalData(resultSet.getInt(ID_ANIMAL),
                        resultSet.getFloat(AGE), resultSet.getString(NAME),
                        resultSet.getString(IMG_URL));
                list.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<AnimalData> getAnimalList(String query) {
        ArrayList<AnimalData> list = new ArrayList();
        AnimalData animal;
        Connection connection = DBConnection.getConnection();
        if(connection == null){
            LOG.info("connection == null");
            return null;
        }
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                animal = getAnimalData(resultSet);
                list.add(animal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static AnimalData getAnimalData(ResultSet rs) throws SQLException {
        return new AnimalData(rs.getInt(ID_ANIMAL),
                rs.getFloat(AGE), rs.getString(NAME),
                rs.getInt(ID_TYPE), rs.getString(IMG_URL));
    }

    public static ArrayList<String> getMediaById(String query) {
        ArrayList<String> list = new ArrayList();
        Connection connection = new DBConnection().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                list.add(resultSet.getString(IMG_URL));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public static boolean isExecuteUpdate(String query) {
        try (Connection connection = new DBConnection().getConnection()) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(query) == SUCCESS;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isExistsUser(String query) {
         Connection connection = new DBConnection().getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                return resultSet.isFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
