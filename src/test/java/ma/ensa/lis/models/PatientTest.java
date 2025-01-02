package ma.ensa.lis.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    private Patient patient;

    @BeforeEach
    void setUp() {
        // Initialisation d'un Patient avec un constructeur complet basé sur vos informations
        patient = new Patient("A12345", "Othmane", "El Azizi", 25, "Homme", "othmane.elazizi@example.com", "123 rue principale", "0612345678");
    }

    @Test
    void testPatientConstructorWithAllParameters() {
        assertNotNull(patient, "L'objet Patient devrait être créé");
        assertEquals("A12345", patient.getCIN(), "Le CIN devrait être correctement initialisé");
        assertEquals("Othmane", patient.getFirstName(), "Le prénom devrait être correctement initialisé");
        assertEquals("El Azizi", patient.getLastName(), "Le nom de famille devrait être correctement initialisé");
        assertEquals(25, patient.getAge(), "L'âge devrait être correctement initialisé");
        assertEquals("Homme", patient.getGender(), "Le sexe devrait être correctement initialisé");
        assertEquals("123 rue principale", patient.getAddress(), "L'adresse devrait être correctement initialisée");
        assertEquals("othmane.elazizi@example.com", patient.getEmail(), "L'email devrait être correctement initialisé");
        assertEquals("0612345678", patient.getPhoneNumber(), "Le numéro de téléphone devrait être correctement initialisé");
    }

    @Test
    void testPatientConstructorWithoutPhoneNumber() {
        // Initialisation d'un Patient sans le téléphone
        Patient patientSansTel = new Patient("B67890", "Yassine", "Zerhouni", 28, "Homme", "yassine.zerhouni@example.com", "456 autre rue");

        assertNull(patientSansTel.getPhoneNumber(), "Le numéro de téléphone devrait être nul s'il n'est pas défini");
    }

    @Test
    void testPatientToString() {
        // Test de la méthode toString
        String expectedString = "Patient(id=null, CIN=A12345, firstName=Othmane, lastName=El Azizi, age=25, gender=Homme, address=123 rue principale, email=othmane.elazizi@example.com, phoneNumber=0612345678, tests=[])";
        assertEquals(expectedString, patient.toString(), "La méthode toString() devrait retourner la chaîne correcte");
    }

    @Test
    void testPatientDefaultConstructor() {
        // Test du constructeur par défaut
        Patient patientParDefaut = new Patient();
        assertNotNull(patientParDefaut, "Le patient par défaut devrait être créé");
        assertNull(patientParDefaut.getCIN(), "Le CIN devrait être nul par défaut");
        assertNull(patientParDefaut.getFirstName(), "Le prénom devrait être nul par défaut");
        assertNull(patientParDefaut.getLastName(), "Le nom de famille devrait être nul par défaut");
        assertEquals(0, patientParDefaut.getAge(), "L'âge devrait être 0 par défaut");
        assertNull(patientParDefaut.getGender(), "Le sexe devrait être nul par défaut");
        assertNull(patientParDefaut.getAddress(), "L'adresse devrait être nulle par défaut");
        assertNull(patientParDefaut.getEmail(), "L'email devrait être nul par défaut");
        assertNull(patientParDefaut.getPhoneNumber(), "Le numéro de téléphone devrait être nul par défaut");
    }

    @Test
    void testSetAndGetTests() {
        // Tester les tests associés au patient
        TestLab test = new TestLab("1", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale");
        patient.getTests().add(test);

        assertEquals(1, patient.getTests().size(), "Le patient devrait avoir 1 test associé");
        assertEquals("Créatinine", patient.getTests().get(0).getName(), "Le nom du test devrait être correctement initialisé");
    }

    @Test
    void testPatientEmailConstructor() {
        // Tester le constructeur avec email
        Patient patientAvecEmail = new Patient("othmane.elazizi@example.com");
        assertNull(patientAvecEmail.getCIN(), "Le CIN devrait être nul lorsque le constructeur avec email est utilisé");
    }
}
