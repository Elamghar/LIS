package ma.ensa.lis.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    String url = "jdbc:mysql://localhost:3306/LisDb";
    String user = "root";
    String password = "root";

    private Connection Conn ;

    public DbConnection() {
        try {
            Conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public Connection getConn(){
            return Conn;
        }
    }