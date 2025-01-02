package ma.ensa.lis.controllers;

import javafx.embed.swing.JFXPanel;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AjoutPatientControllerTest {

    private AjoutPatientController controller;
    private PatientDaoImp mockPatientDao;
    private TestDaoImp mockTestDao;

    @BeforeEach
    void setUp() {
        // Initialisation de JavaFX pour éviter les erreurs de runtime
        new JFXPanel();

        // Création du contrôleur
        controller = new AjoutPatientController();

        // Mocking des DAO
        mockPatientDao = Mockito.mock(PatientDaoImp.class);
        mockTestDao = Mockito.mock(TestDaoImp.class);

        // Simulation des tests disponibles
        List<TestLab> mockTests = new ArrayList<>();
        mockTests.add(new TestLab("1", "Test Sang", "Biologie", "Description 1", false));
        mockTests.add(new TestLab("2", "Test Urine", "Chimie", "Description 2", false));

        when(mockTestDao.findAll()).thenReturn(mockTests);

        // Simulation de l'initialisation des champs
        controller.getTestTableView().setItems(controller.getAvailableTests());
        controller.loadTestsFromDB();
    }

    @Test
    void testValidationDesChampsTousRemplis() {
        // Remplissage des données simulées via les setters
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("Abderrazik");
        controller.getAge().setText("20");
        controller.getCIN().setText("EE922739");
        controller.getNum().setText("0600000000");
        controller.getEmail().setText("othmane232004@gmail.com");
        controller.getAdresse().setText("Marrakech, Gueliz 40100");
        controller.getGenderComboBox().setValue("Homme");

        // Validation des champs
        assertTrue(controller.validateFields(), "Tous les champs sont correctement remplis.");
    }

    @Test
    void testValidationDesChampsManquants() {
        // Remplissage partiel des données
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("");
        controller.getAge().setText("20");

        // Validation des champs
        assertFalse(controller.validateFields(), "La validation échoue lorsque certains champs sont vides.");
    }

    @Test
    void testAjoutPatient() {
        // Remplissage des données simulées
        controller.getNom().setText("Othmane");
        controller.getPrenom().setText("Abderrazik");
        controller.getAge().setText("20");
        controller.getCIN().setText("EE922739");
        controller.getNum().setText("0676934591");
        controller.getEmail().setText("othmane232004@gmail.com");
        controller.getAdresse().setText("Marrakech");
        controller.getGenderComboBox().setValue("Homme");

        // Simulation des tests sélectionnés
        List<TestLab> selectedTests = new ArrayList<>();
        selectedTests.add(new TestLab("1", "Test Sang", "Biologie", "Description 1", true));
        controller.getAvailableTests().addAll(selectedTests);

        // Mock du comportement du DAO
        doNothing().when(mockPatientDao).save(any(Patient.class), anyList());

        // Appel de la méthode à tester
        controller.savePatient();

        // Vérification que le DAO a bien été appelé avec les bonnes données
        verify(mockPatientDao, times(1)).save(any(Patient.class), anyList());
    }

    @Test
    void testChargementDesTestsDepuisLaBaseDeDonnées() {
        // Vérification que les tests sont chargés depuis la base de données
        assertEquals(2, controller.getAvailableTests().size(), "Deux tests doivent être chargés depuis la base de données.");
    }
}
