package ma.ensa.lis.controllers;

import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.util.List;

public class TestControllerTest {

    public static void main(String[] args) {
/*
        // Initialize the TestController
        TestController testController = new TestController();

        // Test createTest
        System.out.println("Testing createTest...");
        try {
            testController.createTest("T002", "MRI Scan", "Radiology", 1500.0f);
            System.out.println("Test created successfully.");
        } catch (Exception e) {
            System.out.println("Error during test creation: " + e.getMessage());
        }

        // Test getTestById
        System.out.println("\nTesting getTestById...");
        try {
            TestLab fetchedTest = testController.getTestById("T002");
            System.out.println(fetchedTest != null ? fetchedTest : "Test not found");
        } catch (Exception e) {
            System.out.println("Error fetching test: " + e.getMessage());
        }

        // Test updateTest
        System.out.println("\nTesting updateTest...");
        try {
            TestLab fetchedTest = testController.getTestById("T002");
            if (fetchedTest != null) {
                fetchedTest.setName("Updated MRI Scan");
                fetchedTest.setPrice(1600.0f);
                testController.updateTest(fetchedTest); // Use TestLab object directly
                System.out.println("Test updated successfully.");

                // Fetch and print updated record
                TestLab updatedTest = testController.getTestById("T002");
                System.out.println(updatedTest != null ? updatedTest : "Test not found");
            } else {
                System.out.println("Test with ID T002 not found, cannot update.");
            }
        } catch (Exception e) {
            System.out.println("Error during test update: " + e.getMessage());
        }

        // Test getAllTests
        System.out.println("\nTesting getAllTests...");
        try {
            List<TestLab> allTests = testController.getAllTests();
            allTests.forEach(test -> System.out.println(test));
        } catch (Exception e) {
            System.out.println("Error fetching all tests: " + e.getMessage());
        }

        // Test deleteTest
        System.out.println("\nTesting deleteTest...");
        try {
            testController.deleteTest("T002");
            System.out.println("Test deleted successfully.");
        } catch (Exception e) {
            System.out.println("Error during test deletion: " + e.getMessage());
        }

        // Verify Deletion
        System.out.println("\nVerifying deletion...");
        try {
            TestLab deletedTest = testController.getTestById("T002");
            System.out.println(deletedTest != null ? "Delete failed" : "Test successfully deleted");
        } catch (Exception e) {
            System.out.println("Error verifying deletion: " + e.getMessage());
        }
    }

 */
    }
}
