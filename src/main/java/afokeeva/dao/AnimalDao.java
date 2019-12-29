package afokeeva.dao;

import afokeeva.Tables.Animal;
import afokeeva.data_base.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnimalDao extends BaseAnimalDao<String, Animal> implements AutoCloseable {

    private DBConnection dbCon = new DBConnection();
    private boolean res = true;
    /*
     SELECT
      `animal`.`id_animal`,
      `animal`.`age`,
      `animal`.`name`,
      `animal`.`description`,
      `animal`.`id_type`,
      `image`.`img_url`
  FROM `animal`
  INNER JOIN  `image` ON `animal`.`id_animal`=`image`.`id_animal`
  INNER JOIN `type` ON `animal`.`id_type`=`type`.`id_type`
  WHERE `type`.`id_type`=1 GROUP BY `animal`.`id_animal`
  */
    @Override
    public ArrayList<Animal> findAll() {
        String query = "SELECT `animal`.`id_animal`,"
                + "`animal`.`age`,"
                + "`animal`.`name`,"
                + "`animal`.`description`,"
                + "`animal`.`id_type`,"
                + "`image`.`img_url`,"
                + "`image`.`id_animal`"
                + " FROM `animal`"
                + " INNER JOIN `image` ON `animal`.`id_animal`=`image`.`id_animal`"
                + " INNER JOIN  `type` ON `animal`.`id_type`=`type`.`id_type`"
                + " GROUP BY `animal`.`id_animal`";
        return getAllAnimals(query);
    }

    public ArrayList<Animal> findAllAnimalsByType(String type) {
         String query = "SELECT `animal`.`id_animal`,"
                + "`animal`.`age`,"
                + "`animal`.`name`,"
                + "`animal`.`description`,"
                + "`animal`.`id_type`,"
                + "`image`.`img_url`,"
                + "`image`.`id_animal`"
                + " FROM `animal`"
                + " INNER JOIN `image` ON `animal`.`id_animal`=`image`.`id_animal`"
                + " INNER JOIN  `type` ON `animal`.`id_type`=`type`.`id_type`"
                + " WHERE `type`.`id_type`=" + type + " GROUP BY `animal`.`id_animal`";
        return getAllAnimals(query);
    }


    public ArrayList<Animal> findAllAnimalsByAge(String from, String to) {
        String query = "SELECT `animal`.`id_animal`,"
                + "`animal`.`age`,"
                + "`animal`.`name`,"
                + "`animal`.`description`,"
                + "`animal`.`id_type`,"
                + "`image`.`img_url`,"
                + "`image`.`id_animal`"
                + " FROM `animal`"
                + " INNER JOIN `image` ON `animal`.`id_animal`=`image`.`id_animal`"
                + " INNER JOIN  `type` ON `animal`.`id_type`=`type`.`id_type`"
                + " WHERE `animal`.`age` BETWEEN " + from + " AND " + to + " GROUP BY `animal`.`id_animal`";
        return getAllAnimals(query );
    }

    public ArrayList<Animal> findAnimalsFilterAgeType(String type, String from, String to) {
         String query = "SELECT `animal`.`id_animal`,"
                + "`animal`.`age`,"
                + "`animal`.`name`,"
                + "`animal`.`description`,"
                + "`animal`.`id_type`,"
                + "`image`.`img_url`,"
                + "`image`.`id_animal`"
                + " FROM `animal`"
                + " INNER JOIN `image` ON `animal`.`id_animal`=`image`.`id_animal`"
                + " INNER JOIN  `type` ON `animal`.`id_type`=`type`.`id_type`"
                + " WHERE `type`.`id_type`=" + type + " AND `animal`.`age` BETWEEN " + from + " AND " + to + " GROUP BY `animal`.`id_animal`";
       return getAllAnimals(query);
    }

    @Override
    public ArrayList<Animal> getAllAnimals(String query) {
        return super.getAllAnimals(query);
    }

    @Override
    public Animal findEntityById(String id) {
        Animal animal=null;
        String query = "SELECT animal.id_animal, animal.age, animal.name, animal.description, animal.type image.jpg_url " +
                       "FROM animal, image " +
                       "INNER JOIN  image ON animal.id_animal=image.id_animal " +
                       "WHERE animal.id_animal=" + id;
        try (Connection con = dbCon.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                animal = new Animal(rs.getString("id_animal"),
                        rs.getString("age"), rs.getString("name"),
                        rs.getString("description"), rs.getString("id_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }
    /**
     * get description and all images of animal
     * @param id
     * @return
     */
    public Animal findEntityImagesAndDescriptionById(String id) {
        Animal animal=null;
        ArrayList<String> list = new ArrayList<>();
        String des="";
        String query = "SELECT animal.description " +
                "FROM animal" +
                " WHERE animal.id_animal=" + id +
                " UNION SELECT `image`.`img_url` from `image`  where `image`.`id_animal`=1";
        try (Connection con = dbCon.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                if(rs.isFirst()) {
                    des = rs.getString("description");
                }else {
                    list.add(rs.getString("description"));
            }}
            animal = new Animal(des, list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return animal;
    }


    @Override
    public boolean add(Animal entity) {
        String query = "INSERT INTO `animals`.`animal` (`age`, `name`, `description`, `id_type`)" +
                " VALUES (" + entity.getAge() + ",'" + entity.getName() + "','" + entity.getDescription() + "','" + entity.getType() + "')";
        return res = executeUpdate(query);
    }

    @Override
    public boolean update(Animal entity) {
        String query = "UPDATE `animals`.`animal` SET `age`="+ entity.getAge() + ", "
                + " `name`='" + entity.getName() + "', "
                + " `description`='" + entity.getDescription() +"'"
                + " `id_type`='" + entity.getType() +"'";
        return res = executeUpdate(query);
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM `animals`.`animal` WHERE `id_animal`=" + id;
        res = executeUpdate(query);
        return res;
    }

    @Override
    public boolean executeUpdate(String query) {
        return super.executeUpdate(query);
    }

    @Override
    public void close() throws Exception {
    }
}


