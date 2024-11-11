package ma.ensa.lis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnexion {
    String url = "jdbc:mysql://localhost:3306/LisDb";
    String user = "root";
    String password = "root";

    private Connection conn ;

    public void ConnectionDb() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
