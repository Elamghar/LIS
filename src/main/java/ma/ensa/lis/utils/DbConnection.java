package ma.ensa.lis.utils;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    String url = "jdbc:mysql://localhost:3306/LisDb";
    String user = "root";
    String password = "root";

    @Getter
    private Connection conn ;

    public DbConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
/*    Singleton Pattern for DbConnection

    public class DbConnection {
        private static DbConnection instance;
        private Connection conn;

        private DbConnection() {
            // Initialize connection
        }

        public static DbConnection getInstance() {
            if (instance == null) {
                instance = new DbConnection();
            }
            return instance;
        }

        public Connection getConn() {
            return conn;
        }
    }

 */


}
