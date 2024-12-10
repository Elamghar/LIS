<<<<<<< HEAD
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
=======
package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.QRCodeGenerator;

import java.util.Date;

public class TestController {

    @FXML
    private TextField idField, nameField, categoryField, priceField;

    @FXML
    private Button createBtn, updateBtn, deleteBtn;

    private final TestDaoImp testDao;

    public TestController() {
        DbConnection dbConnection = new DbConnection();
        this.testDao = new TestDaoImp(dbConnection);
    }

    @FXML
    public void initialize() {
        // Optional: Initialize any required fields or states here
    }

    @FXML
    private void createTest(ActionEvent actionEvent) {
        String id = idField.getText();
        String name = nameField.getText();
        String category = categoryField.getText();
        float price = 0;

        try {
            price = Float.parseFloat(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price must be a number.");
            return;
        }

        if (id.isEmpty() || name.isEmpty() || category.isEmpty()) {
            showAlert("Invalid Input", "Please fill in all fields.");
            return;
        }

        TestLab newTest = new TestLab(id, name, new Date(), price, category, new Date());
        testDao.save(newTest);

        // Generate QR Code
        String qrData = String.format("TestLab ID: %s\nName: %s\nCategory: %s\nPrice: %.2f\nDate: %s", id, name, category, price, new Date());
        String filePath = "qrcodes/" + id + "_qrcode.png";
        QRCodeGenerator.generateQRCode(qrData, filePath, 300, 300);

        showAlert("Test Created", "The test has been successfully created.");
    }

    @FXML
    private void updateTest(ActionEvent actionEvent) {
        String id = idField.getText();
        String name = nameField.getText();
        String category = categoryField.getText();
        float price = 0;

        try {
            price = Float.parseFloat(priceField.getText());
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Price must be a number.");
            return;
        }

        if (id.isEmpty() || name.isEmpty() || category.isEmpty()) {
            showAlert("Invalid Input", "Please fill in all fields.");
            return;
        }

        TestLab testToUpdate = new TestLab(id, name, new Date(), price, category, new Date());
        testDao.update(testToUpdate);

        // Generate QR Code for updated test
        String qrData = String.format("TestLab ID: %s\nName: %s\nCategory: %s\nPrice: %.2f\nDate: %s", id, name, category, price, new Date());
        String filePath = "qrcodes/" + id + "_qrcode.png";
        QRCodeGenerator.generateQRCode(qrData, filePath, 300, 300);

        showAlert("Test Updated", "The test has been successfully updated.");
    }

    @FXML
    private void deleteTest(ActionEvent actionEvent) {
        String id = idField.getText();

        if (id.isEmpty()) {
            showAlert("Invalid Input", "Please enter a valid Test ID to delete.");
            return;
        }

        testDao.delete(id);
        showAlert("Test Deleted", "The test has been successfully deleted.");
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        // Logic for logging out --> return to log in screen "zakariae"
        System.out.println("Logged out");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
>>>>>>> origin/main
