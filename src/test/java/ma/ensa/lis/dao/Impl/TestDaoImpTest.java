package ma.ensa.lis.dao.Impl;

import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class TestDaoImpTest {

    private TestDaoImp testDaoImp;
    private DbConnection dbConnection;

    @BeforeEach
    public void setUp() {
        dbConnection = new DbConnection();
        testDaoImp = new TestDaoImp(dbConnection);
    }

    @Test
    void testAddAndRetrieveAntiHBsTest() throws SQLException {
        // Créer un test pour les anticorps anti-HBs
        TestLab test = new TestLab("T004", "Anticorps anti-HBs", "Sérologie", "Évaluation de l’immunité contre l’hépatite B.");

        // Ajouter le test dans la base de données
        testDaoImp.save(test);

        // Récupérer le test depuis la base de données
        TestLab retrievedTest = testDaoImp.findById("T004");

        // Vérifier que le test a été ajouté correctement
        assertNotNull(retrievedTest, "Le test ne doit pas être nul.");
        assertEquals("T004", retrievedTest.getId(), "L'ID du test doit être T004.");
        assertEquals("Anticorps anti-HBs", retrievedTest.getName(), "Le nom du test doit être 'Anticorps anti-HBs'.");
        assertEquals("Sérologie", retrievedTest.getCategory(), "La catégorie du test doit être 'Sérologie'.");
        assertEquals("Évaluation de l’immunité contre l’hépatite B.", retrievedTest.getDescription(), "La description doit correspondre.");

        // Nettoyage après le test (suppression du test inséré)
        testDaoImp.delete("T004");
    }

    @Test
    void testFindAllTests() throws SQLException {
        // Ajouter quelques tests à la base de données
        TestLab test1 = new TestLab("T001", "Test 1", "Category 1", "Description 1");
        TestLab test2 = new TestLab("T002", "Test 2", "Category 2", "Description 2");
        testDaoImp.save(test1);
        testDaoImp.save(test2);

        // Vérifier la récupération de tous les tests
        var tests = testDaoImp.findAll();
        assertNotNull(tests, "La liste des tests ne doit pas être nulle.");
        assertTrue(tests.size() > 0, "Il doit y avoir des tests dans la liste.");

        // Nettoyage après le test (suppression des tests insérés)
        testDaoImp.delete("T001");
        testDaoImp.delete("T002");
    }
}
