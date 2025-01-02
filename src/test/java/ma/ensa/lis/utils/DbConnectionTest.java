package ma.ensa.lis.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {

    private DbConnection dbConnection;

    @BeforeEach
    void setUp() {
        // Initialisation de l'objet DbConnection avant chaque test
        dbConnection = new DbConnection();
    }

    @Test
    void testDbConnectionConstructor() {
        // Tester si la connexion a été établie avec succès
        Connection conn = dbConnection.getConn();
        assertNotNull(conn, "La connexion à la base de données devrait être établie");
    }

    @Test
    void testConnectionNotNull() {
        // Vérifier que la connexion n'est pas null
        Connection conn = dbConnection.getConn();
        assertNotNull(conn, "La connexion ne doit pas être null");
    }

    @Test
    void testCloseConnection() {
        // Tester la méthode de fermeture de la connexion
        dbConnection.closeConnection();
        Connection conn = dbConnection.getConn();
        try {
            assertTrue(conn.isClosed(), "La connexion devrait être fermée après appel de closeConnection()");
        } catch (Exception e) {
            fail("La connexion n'a pas été fermée correctement.");
        }
    }

    @Test
    void testMultipleConnections() {
        // Tester la création de plusieurs connexions pour s'assurer que la méthode de connexion est correcte
        DbConnection anotherDbConnection = new DbConnection();
        Connection conn1 = dbConnection.getConn();
        Connection conn2 = anotherDbConnection.getConn();

        assertNotSame(conn1, conn2, "Chaque nouvelle instance de DbConnection devrait créer une connexion distincte");

        dbConnection.closeConnection();
        anotherDbConnection.closeConnection();
    }
}

