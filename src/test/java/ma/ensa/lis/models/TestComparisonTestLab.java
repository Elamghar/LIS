package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestComparisonTestLab {

    @Test
    public void testCompareTest() {
        // Création de deux tests identiques
        TestLab test1 = new TestLab("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());
        TestLab test2 = new TestLab("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());

        // Vérification qu'ils sont considérés comme égaux
        assertTrue(test1.compareTest(test2));

        // Modification de l'un des tests
        test2.setName("Test Cholesterol");

        // Vérification qu'ils ne sont plus égaux
        assertFalse(test1.compareTest(test2));
    }
}

