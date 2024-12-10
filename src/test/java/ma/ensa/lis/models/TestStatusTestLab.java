package ma.ensa.lis.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ma.ensa.lis.models.TestStatus;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class TestStatusTestLab {

    @Test
    public void testStartTest() {
        // Création d'un test avec statut PENDING
        TestLab test = new TestLab("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());

        // Vérification que le statut initial est PENDING
        assertEquals(TestStatus.PENDING, test.getStatus());

        // Lancer le test
        test.startTest();

        // Vérification que le statut est passé à IN_PROGRESS
        assertEquals(TestStatus.IN_PROGRESS, test.getStatus());
    }

    @Test
    public void testCompleteTest() {
        // Création d'un test avec statut IN_PROGRESS
        TestLab test = new TestLab("T123", "Test Glucose", new Date(), 15.5f, "Blood", new Date());
        test.startTest();  // Passer à IN_PROGRESS

        // Vérification que le statut est IN_PROGRESS
        assertEquals(TestStatus.IN_PROGRESS, test.getStatus());

        // Compléter le test
        test.completeTest();

        // Vérification que le statut est passé à COMPLETED
        assertEquals(TestStatus.COMPLETED, test.getStatus());
    }

    @Test
    public void testDaysUntilCompletion() {
        // Créer une date de test et une date de complétion
        Date testDate = new Date();
        Date expectedCompletionDate = new Date(testDate.getTime() + 100000000);  // 100 000 000 ms plus tard

        // Créer un test
        TestLab test = new TestLab("T123", "Test Glucose", testDate, 15.5f, "Blood", expectedCompletionDate);

        // Vérifier les jours restants jusqu'à la date de complétion
        long daysRemaining = test.daysUntilCompletion();
        long expectedDaysRemaining = (expectedCompletionDate.getTime() - testDate.getTime()) / (1000 * 60 * 60 * 24);

        assertEquals(expectedDaysRemaining, daysRemaining);
    }
}

