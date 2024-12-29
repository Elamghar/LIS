package ma.ensa.lis.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    String url = "jdbc:mysql://localhost:3306/LisDb";
    String user = "root";
    String password = "root";

    private Connection Conn;

    // Constructeur pour initialiser la connexion
    public DbConnection() {
        try {
            Conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
        } catch (SQLException e) {
            System.err.println("Échec de la connexion à la base de données.");
            e.printStackTrace();
        }
    }

    // Retourne la connexion
    public Connection getConn() {
        return Conn;
    }

    // Méthode pour fermer la connexion
    public void closeConnection() {
        try {
            if (Conn != null && !Conn.isClosed()) {
                Conn.close();
                System.out.println("Connexion fermée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
