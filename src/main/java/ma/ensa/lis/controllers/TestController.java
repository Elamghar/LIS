package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import ma.ensa.lis.models.Test;
import ma.ensa.lis.repositories.TestRepository;

import java.util.Date;
import java.util.List;

public class TestController {

    private TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public void createTest(String id, String name, String category, float price) {
        Test test = new Test(id, name, new Date(), price, category, new Date());
        testRepository.save(test);
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test getTestById(String id) {
        return testRepository.findById(id);
    }

    public void updateTest(Test test) {
        testRepository.update(test);
    }

    public void deleteTest(String id) {
        testRepository.delete(id);
    }

    public void logout(ActionEvent actionEvent) {
    }
}
