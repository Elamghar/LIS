package ma.ensa.lis.controllers;

import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.TableView;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    private AdminController controller;
    private DbConnection mockDbConnection;
    private Connection mockConnection;
    private Statement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws Exception {
        // Initialiser JavaFX pour éviter les erreurs de runtime
        new JFXPanel();

        // Initialiser le contrôleur
        controller = new AdminController();

        // Mock de DbConnection, Connection, Statement et ResultSet
        mockDbConnection = Mockito.mock(DbConnection.class);
        mockConnection = Mockito.mock(Connection.class);
        mockStatement = Mockito.mock(Statement.class);
        mockResultSet = Mockito.mock(ResultSet.class);

        // Configurer le comportement des mocks
        when(mockDbConnection.getConn()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        // Simuler les données renvoyées par la base de données
        when(mockResultSet.next()).thenReturn(true, true, false); // 2 patients
        when(mockResultSet.getString("CIN")).thenReturn("EE922739", "AB123456");
        when(mockResultSet.getString("firstName")).thenReturn("Othmane", "Sarah");
        when(mockResultSet.getString("lastName")).thenReturn("Abderrazik", "Doe");
        when(mockResultSet.getString("gender")).thenReturn("Homme", "Femme");
    }

    @Test
    void testLoadPatientDetails() throws Exception {
        // Injecter le mock DbConnection dans le contrôleur
        controller.loadPatientDetails();

        // Vérifier que les patients sont chargés dans la table
        TableView<Patient> table = controller.getTable(); // Ajoutez un getter pour accéder à la table dans le test
        ObservableList<Patient> patients = table.getItems();

        assertEquals(2, patients.size(), "Deux patients doivent être chargés.");
        assertEquals("Othmane", patients.get(0).getFirstName(), "Le premier patient doit être Othmane.");
        assertEquals("Sarah", patients.get(1).getFirstName(), "Le deuxième patient doit être Sarah.");

        // Vérifier que les connexions à la base de données sont fermées
        verify(mockDbConnection, times(1)).closeConnection();
    }

    @Test
    void testNavigationToLogin() throws Exception {
        // Simuler un événement ActionEvent et vérifier que la méthode fonctionne sans exception
        assertDoesNotThrow(() -> controller.logout(mock(ActionEvent.class)),
                "La navigation vers la vue de connexion ne doit pas lever d'exception.");
    }

    @Test
    void testNavigationToCreatePatient() throws Exception {
        // Simuler un événement ActionEvent et vérifier que la méthode fonctionne sans exception
        assertDoesNotThrow(() -> controller.createPatient(mock(ActionEvent.class)),
                "La navigation vers la vue de création de patient ne doit pas lever d'exception.");
    }

    @Test
    void testNavigationToDeletePatient() throws Exception {
        // Simuler un événement ActionEvent et vérifier que la méthode fonctionne sans exception
        assertDoesNotThrow(() -> controller.deletePatient(mock(ActionEvent.class)),
                "La navigation vers la vue de suppression de patient ne doit pas lever d'exception.");
    }

    @Test
    void testNavigationToSeeMedicalFile() throws Exception {
        // Simuler un événement ActionEvent et vérifier que la méthode fonctionne sans exception
        assertDoesNotThrow(() -> controller.seeMedicalFile(mock(ActionEvent.class)),
                "La navigation vers la vue des dossiers médicaux ne doit pas lever d'exception.");
    }
}
