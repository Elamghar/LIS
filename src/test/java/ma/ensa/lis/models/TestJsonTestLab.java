package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJsonTestLab {

    @Test
    public void testToJson() {
        // Création d'un test
        Date testDate = new Date();
        Date expectedCompletionDate = new Date(testDate.getTime() + 1000000); // 1 million ms plus tard
        TestLab test = new TestLab("T123", "Test Glucose", testDate, 15.5f, "Blood", expectedCompletionDate);

        // Conversion de l'objet en JSON
        String json = test.toJson();

        // Vérification que le JSON contient certains champs
        assertTrue(json.contains("id"));
        assertTrue(json.contains("name"));
        assertTrue(json.contains("category"));
        assertTrue(json.contains("price"));
    }
}

