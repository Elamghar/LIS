package ma.ensa.lis.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestTestLab {

    @Test
    public void testTestConstructor() {
        // Créer une date pour le test
        Date testDate = new Date();
        Date expectedCompletionDate = new Date(testDate.getTime() + 1000000);  // Ajouter 1 000 000 ms

        // Création de l'objet Test
        TestLab test = new TestLab("T123", "Test Glucose", testDate, 15.5f, "Blood", expectedCompletionDate);

        // Vérifier les valeurs des attributs
        assertEquals("T123", test.getId());
        assertEquals("Test Glucose", test.getName());
        assertEquals("Blood", test.getCategory());
        assertEquals(testDate, test.getTestDate());
        assertEquals(expectedCompletionDate, test.getExpectedCompletionDate());
        assertEquals(TestStatus.PENDING, test.getStatus());  // Vérifier le statut par défaut
    }

    @Test
    public void testSettersAndGetters() {
        // Création d'un objet Test
        TestLab test = new TestLab("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());

        // Modification des attributs via les setters
        test.setName("Test Cholesterol");
        test.setCategory("Blood");
        test.setPrice(20.0f);

        // Vérification des nouvelles valeurs avec les getters
        assertEquals("Test Cholesterol", test.getName());
        assertEquals("Blood", test.getCategory());
        assertEquals(20.0f, test.getPrice());
    }
}

