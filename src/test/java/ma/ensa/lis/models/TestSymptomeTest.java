package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestSymptomeTest {

    @Test
    public void testAddSymptome() {
        // Création d'un test
        Test test = new Test("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());

        // Ajouter un symptôme
        Symptome symptome = new Symptome("Cough");
        test.addSymptome(symptome);

        // Vérifier que le symptôme a bien été ajouté
        assertEquals(1, test.getSymptomes().size());
        assertTrue(test.getSymptomes().contains(symptome));
    }
}

