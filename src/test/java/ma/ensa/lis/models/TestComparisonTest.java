package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestComparisonTest {

    @Test
    public void testCompareTest() {
        // Création de deux tests identiques
        Test test1 = new Test("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());
        Test test2 = new Test("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());

        // Vérification qu'ils sont considérés comme égaux
        assertTrue(test1.compareTest(test2));

        // Modification de l'un des tests
        test2.setName("Test Cholesterol");

        // Vérification qu'ils ne sont plus égaux
        assertFalse(test1.compareTest(test2));
    }
}

