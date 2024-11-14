package ma.ensa.lis.controllers;

import ma.ensa.lis.models.Test;
import ma.ensa.lis.services.TestService;

import java.util.Date;
import java.util.List;

public class TestController {

    private TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    public void createTest(String id, String name, String category, float price) {
        Test test = new Test(id, name, new Date(), price, category, new Date());
        testService.createTest(test);
    }

    public List<Test> getAllTests() {
        return testService.getAllTests();
    }

    public Test getTestById(String id) {
        return testService.getTestById(id);
    }

    public void updateTest(Test test) {
        testService.updateTest(test);
    }

    public void deleteTest(String id) {
        testService.deleteTest(id);
    }
}
