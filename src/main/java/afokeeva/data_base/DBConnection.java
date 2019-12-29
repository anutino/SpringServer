package afokeeva.data_base;

import java.sql.*;

 public class DBConnection {

    private final String url = "jdbc:mysql://127.0.0.1:3306/animals?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String user = "root";
    private final String password = "Twentyfour1";
    private Connection connection;

    public Connection getConnection() {
         try {
             connection = DriverManager.getConnection(url, user, password);
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return connection;
     }
}
