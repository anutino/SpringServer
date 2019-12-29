package afokeeva.dao;

import afokeeva.Tables.Animal;
import afokeeva.data_base.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class BaseAnimalDao<K extends String, T> implements Dao<String, T>, AutoCloseable{
     @Override
     public ArrayList findAll() {
          return null;
     }

     @Override
     public T findEntityById(String id) {
          return null;
     }

     @Override
     public boolean add(T entity) {
          return false;
     }

     @Override
     public boolean delete(String id) {
          return false;
     }

     @Override
     public boolean update(T entity) {
          return false;
     }

     /**
      * Use for  findAll(), findAllAnimalsByType(String type), findAllAnimalsByAge(String from, String to), findAnimalsFilterAgeType( String type, String from, String to)
      * @return ArrayList<Animal>
      */
     public ArrayList<Animal> getAllAnimals(String query){
          ArrayList<Animal> list = new ArrayList();
          Animal animal;
          try (Connection con = new DBConnection().getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
               while (rs.next()) {
                    animal = new Animal(rs.getString("id_animal"),
                            rs.getString("age"), rs.getString("name"),
                            rs.getString("description"), rs.getString("id_type"),rs.getString("img_url"));
                    list.add(animal);
               }
          } catch (SQLException e) {
               e.printStackTrace();
          }
          System.out.println("Total number of animals: " + list.size());
          return list;
     }

     public boolean executeUpdate(String query){
          boolean res = true;
          try (Connection con = new DBConnection().getConnection(); Statement stmt = con.createStatement()) {
               int x = stmt.executeUpdate(query);
               if(x > 0) {
                    System.out.println("Successfully updated");
               }else {
                    System.out.println("ExecuteUpdate ERROR");
                    res = false;
               }
          }catch (SQLException e) {
               res = false;
               e.printStackTrace();
          }
          return res;
     }

     @Override
     public void close() throws Exception {
     }
 }
