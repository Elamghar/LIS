package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Test;
import ma.ensa.lis.utils.DbConnection;

import java.util.Date;
import java.util.List;

public class TestController {

    private final TestDaoImp testDao;

    public TestController(DbConnection dbConnection) {
        this.testDao = new TestDaoImp(dbConnection);
    }

    public void createTest(String id, String name, String category, float price) {
        Test test = new Test(id, name, new Date(), price, category, new Date());
        testDao.save(test);
    }

    public List<Test> getAllTests() {
        return testDao.findAll();
    }

    public Test getTestById(String id) {
        return testDao.findById(id);
    }

    public void updateTest(Test test) {
        testDao.update(test);
    }

    public void deleteTest(String id) {
        testDao.delete(id);
    }

    public void logout(ActionEvent actionEvent) {
        // Ajouter la logique de déconnexion si nécessaire.
    }
}
