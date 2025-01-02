package ma.ensa.lis.controllers;

import javafx.embed.swing.JFXPanel;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AjoutPatientControllerTest {

    private AjoutPatientController controller;
    private PatientDaoImp patientDao;
    private TestDaoImp testDao;

    @BeforeEach
    void setUp() {
        // Initialize JavaFX environment for testing
        new JFXPanel();

        // Create the controller
        controller = new AjoutPatientController();

        // Create the DAO objects (in this case, real instances)
        patientDao = new PatientDaoImp();
        testDao = new TestDaoImp();

        // Simulate the loading of available tests into the controller
        controller.getTestTableView().setItems(controller.getAvailableTests());
        controller.loadTestsFromDB();
    }

    @Test
    void testValidationDesChampsTousRemplis() {
        // Set input data for patient
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("Abderrazik");
        controller.getAge().setText("20");
        controller.getCIN().setText("EE922739");
        controller.getNum().setText("0600000000");
        controller.getEmail().setText("othmane232004@gmail.com");
        controller.getAdresse().setText("Marrakech, Gueliz 40100");
        controller.getGenderComboBox().setValue("Homme");

        // Validate all fields
        assertTrue(controller.validateFields(), "Tous les champs sont correctement remplis.");
    }

    @Test
    void testValidationDesChampsManquants() {
        // Set partial data for patient
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("");
        controller.getAge().setText("20");

        // Validate fields
        assertFalse(controller.validateFields(), "La validation échoue lorsque certains champs sont vides.");
    }

    @Test
    void testAjoutPatient() {
        // Set input data for the patient
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("Abderrazik");
        controller.getAge().setText("20");
        controller.getCIN().setText("EE922739");
        controller.getNum().setText("0676934591");
        controller.getEmail().setText("othmane232004@gmail.com");
        controller.getAdresse().setText("Marrakech");
        controller.getGenderComboBox().setValue("Homme");

        // Simulate selected tests
        List<TestLab> selectedTests = new ArrayList<>();
        selectedTests.add(new TestLab("1", "Test Sang", "Biologie", "Description 1", true));
        selectedTests.add(new TestLab("2", "Test Urine", "Chimie", "Description 2", true));

        controller.getAvailableTests().addAll(selectedTests);

        // Save patient and associated tests to the database
        patientDao.save(new Patient("EE922739", "Othmane", "Abderrazik", 20, "Homme", "othmane232004@gmail.com", "Marrakech", "0600000000"), selectedTests);

        // Check if the patient was saved in the database (you can also verify in the actual DB)
        // For now, let's simulate this by checking if the data is saved
        // You can also check if the data was inserted by querying the DB
        assertNotNull(patientDao.findByCIN("EE922739"), "Patient should be saved with the provided CIN.");
    }

    @Test
    void testChargementDesTestsDepuisLaBaseDeDonnées() {
        // Ensure tests are loaded from the database (simulated)
        List<TestLab> testsFromDB = testDao.findAll();
        assertEquals(2, testsFromDB.size(), "Deux tests doivent être chargés depuis la base de données.");
    }
}
