package ma.ensa.lis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    String url = "jdbc:mysql://localhost:3306/LisDb";
    String user = "root";
    String password = "root";

    private Connection conn ;

    public DbConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("connexion reussi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Connection getConn(){
        return  conn;
    }
}
