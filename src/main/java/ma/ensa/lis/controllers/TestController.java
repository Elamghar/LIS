package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.QRCodeGenerator;

import java.util.Date;
import java.util.List;

public class TestController {

    private final TestDaoImp testDao;

    public TestController(DbConnection dbConnection) {
        this.testDao = new TestDaoImp(dbConnection);
    }

    public void createTest(String id, String name, String category, float price) {

        TestLab testLab = new TestLab(id, name, new Date(), price, category, new Date());
        testDao.save(testLab);

        //Générer le code QR
        String qrData = String.format("TestLab ID: %s\nName: %s\nCategory: %s\nPrice: %.2f\nDate: %s", id, name, category, price, new Date());
        String filePath = "qrcodes/" + id + "_qrcode.png";
        QRCodeGenerator.generateQRCode(qrData, filePath, 300, 300);

    }

    public List<TestLab> getAllTests() {
        return testDao.findAll();
    }

    public TestLab getTestById(String id) {
        return testDao.findById(id);
    }

    public void updateTest(TestLab test) {

        testDao.update(test);

        //Générer un nouveau QR code pour les mises à jour!
        String qrData = String.format("TestLab ID: %s\nName: %s\nCategory: %s\nPrice: %.2f\nDate: %s", 
                                      test.getId(), test.getName(), test.getCategory(), test.getPrice(), test.getTestDate());
        String filePath = "qrcodes/" + test.getId() + "_qrcode.png";
        QRCodeGenerator.generateQRCode(qrData, filePath, 300, 300);

    }

    public void deleteTest(String id) {
        testDao.delete(id);
    }

    public void logout(ActionEvent actionEvent) {
        // Ajouter la logique de déconnexion si nécessaire.
    }
}
