package ma.ensa.lis.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestLabTest {

    @Test
    void testConstructorAndGetters() {
        // Création d'un objet TestLab
        TestLab test = new TestLab("18", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale.");

        // Assertions pour vérifier les valeurs initiales
        assertEquals("18", test.getId());
        assertEquals("Créatinine", test.getName());
        assertEquals("Néphrologie", test.getCategory());
        assertEquals("Évaluation de la fonction rénale.", test.getDescription());
    }

    @Test
    void testSetters() {
        // Création d'un objet TestLab
        TestLab test = new TestLab();

        // Modification des valeurs
        test.setId("19");
        test.setName("Glucose");
        test.setCategory("Biochimie");
        test.setDescription("Analyse du taux de sucre dans le sang.");

        // Assertions pour vérifier les modifications
        assertEquals("19", test.getId());
        assertEquals("Glucose", test.getName());
        assertEquals("Biochimie", test.getCategory());
        assertEquals("Analyse du taux de sucre dans le sang.", test.getDescription());
    }

    @Test
    void testBooleanProperty() {
        // Création d'un objet TestLab
        TestLab test = new TestLab();

        // Vérification de l'état initial
        assertFalse(test.isSelected());

        // Modification de la propriété selected
        test.setSelected(true);

        // Vérification après modification
        assertTrue(test.isSelected());
    }

    @Test
    void testEqualsAndHashCode() {
        // Création de deux objets TestLab identiques
        TestLab test1 = new TestLab("18", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale.");
        TestLab test2 = new TestLab("18", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale.");

        // Vérification de l'égalité et du hashCode
        assertEquals(test1, test2);
        assertEquals(test1.hashCode(), test2.hashCode());
    }

    @Test
    void testToString() {
        // Création d'un objet TestLab
        TestLab test = new TestLab("18", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale.");

        // Vérification de la représentation en chaîne
        String expected = "TestLab(id=18, name=Créatinine, category=Néphrologie, description=Évaluation de la fonction rénale., selected=BooleanProperty [value: false])";
        assertEquals(expected, test.toString());
    }
}
