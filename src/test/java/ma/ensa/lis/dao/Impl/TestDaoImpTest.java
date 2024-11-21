package ma.ensa.lis.dao.Impl;

import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.models.TestStatus;
import ma.ensa.lis.utils.DbConnection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestDaoImpTest {

    public static void main(String[] args) throws ParseException {

        // Initialiser la connexion à la base de données
        DbConnection dbConnection = new DbConnection();
        TestDaoImp testDao = new TestDaoImp(dbConnection);

        // Préparer le formatteur de date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Test Save
        System.out.println("Testing Save...");
        TestLab newTest = new TestLab("T001", "Blood Test", sdf.parse("2024-11-15"), 300.0f, "Laboratory", sdf.parse("2024-11-22"));
        testDao.save(newTest);

        // Test findByID
        System.out.println("\nTesting Find By ID...");
        TestLab fetchedTest = testDao.findById("T001");
        System.out.println(fetchedTest != null ? fetchedTest : "Test not found");

        // Test update
        System.out.println("\nTesting Update...");
        if (fetchedTest != null) {
            fetchedTest.setName("Updated Blood Test");
            fetchedTest.setStatus(TestStatus.IN_PROGRESS);
            testDao.update(fetchedTest);

            // Fetch and print updated record
            TestLab updatedTest = testDao.findById("T001");
            System.out.println(updatedTest != null ? updatedTest : "Test not found");
        }

        // Test findAll
        System.out.println("\nTesting Find All...");
        List<TestLab> allTests = testDao.findAll();
        allTests.forEach(System.out::println);

        // Test delete
        System.out.println("\nTesting Delete...");
        testDao.delete("T001");

        // Verify Deletion
        TestLab deletedTest = testDao.findById("T001");
        System.out.println(deletedTest != null ? "Delete failed" : "Test successfully deleted");
    }
}
