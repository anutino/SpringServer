package afokeeva.dao;

import afokeeva.Tables.User;
import afokeeva.data_base.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDao extends BaseAnimalDao<String, User> implements AutoCloseable{

    private DBConnection dbCon = new DBConnection();
    private boolean res = true;

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> list = new ArrayList();
        User user;
        String query = "SELECT * FROM `animals`.`user`";
        try (Connection con = dbCon.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                user = new User(rs.getString("id_user"), rs.getString("name"));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Total number of users: " + list.size());
        return list;
    }

    @Override
    public User findEntityById(String id) {
        User user=null;
        String query = "SELECT users.* FROM user WHERE users.id_user=" + id +"";
        System.out.println(query);
        try (Connection con = dbCon.getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                user = new User(rs.getString("id_user"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean add(User entity) {
        String query = "INSERT INTO `animals`.`user` (`name`) VALUES ('" + entity.getName() + "')";
        return res = executeUpdate(query);
    }

    @Override
    public boolean update(User entity) {
        String query = "UPDATE `animals`.`user` SET `name`='" + entity.getName() + "')";
        return res = executeUpdate(query);
    }

    @Override
    public boolean delete(String id) {
        String query = "DELETE FROM `animals`.`user` WHERE `id_user`=" + id;
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
