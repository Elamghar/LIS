package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.util.Date;
import java.util.List;

public class TestController {

    private final TestDaoImp testDao;

    public TestController(DbConnection dbConnection) {
        this.testDao = new TestDaoImp(dbConnection);
    }

    public void createTest(String id, String name, String category, float price) {
        TestLab test = new TestLab(id, name, new Date(), price, category, new Date());
        testDao.save(test);
    }

    public List<TestLab> getAllTests() {
        return testDao.findAll();
    }

    public TestLab getTestById(String id) {
        return testDao.findById(id);
    }

    public void updateTest(TestLab test) {
        testDao.update(test);
    }

    public void deleteTest(String id) {
        testDao.delete(id);
    }

    public void logout(ActionEvent actionEvent) {
        // Ajouter la logique de déconnexion si nécessaire.
    }
}
