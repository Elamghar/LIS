package ma.ensa.lis.controllers;

import ma.ensa.lis.controllers.TestController;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.util.List;

public class TestControllerTest {

    public static void main(String[] args) {

        // Initialize database connection
        DbConnection dbConnection = new DbConnection();

        // Initialize the TestController
        TestController testController = new TestController(dbConnection);

        // Test createTest
        System.out.println("Testing createTest...");
        testController.createTest("T002", "MRI Scan", "Radiology", 1500.0f);

        // Test getTestById
        System.out.println("\nTesting getTestById...");
        TestLab fetchedTest = testController.getTestById("T002");
        System.out.println(fetchedTest != null ? fetchedTest : "Test not found");

        // Test updateTest
        System.out.println("\nTesting updateTest...");
        if (fetchedTest != null) {
            fetchedTest.setName("Updated MRI Scan");
            fetchedTest.setPrice(1600.0f);
            testController.updateTest(fetchedTest);

            // Fetch and print updated record
            TestLab updatedTest = testController.getTestById("T002");
            System.out.println(updatedTest != null ? updatedTest : "Test not found");
        }

        // Test getAllTests
        System.out.println("\nTesting getAllTests...");
        List<TestLab> allTests = testController.getAllTests();
        allTests.forEach(System.out::println);

        // Test deleteTest
        System.out.println("\nTesting deleteTest...");
        testController.deleteTest("T002");

        // Verify Deletion
        TestLab deletedTest = testController.getTestById("T002");
        System.out.println(deletedTest != null ? "Delete failed" : "Test successfully deleted");
    }
}

