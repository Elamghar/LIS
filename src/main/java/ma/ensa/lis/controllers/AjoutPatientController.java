package ma.ensa.lis.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

public class AjoutPatientController {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField num;
    @FXML
    private TextField genre;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;

    @FXML
    private TableView<TestLab> testTableView;
    @FXML
    private TableColumn<TestLab, String> testNomCol;
    @FXML
    private TableColumn<TestLab, String> testCatCol;
    @FXML
    private TableColumn<TestLab, Boolean> testSelectCol;
    @FXML
    private ObservableList<TestLab> availableTests = FXCollections.observableArrayList();

    private TestDaoImp testDao;

    public void initialize() {

        DbConnection dbConnection = new DbConnection();
        testDao = new TestDaoImp(dbConnection);

        // Configuration des colonnes de la table
        testNomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        testCatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        testSelectCol.setCellValueFactory(cellData -> cellData.getValue().getSelectedProperty());
        testSelectCol.setCellFactory(CheckBoxTableCell.forTableColumn(testSelectCol));

        // Charger les tests depuis la base de données
        loadTests();

        // Lier les tests disponibles à la table
        testTableView.setItems(availableTests);
    }

    private void loadTests() {
        List<TestLab> tests = testDao.findAll();
        availableTests.addAll(tests);
    }

    private void writeInFile() throws IOException {
        FileWriter f = new FileWriter("infosurpatient.txt");
        String s = nom.getText() + "," + prenom.getText() + "," + age.getText();
        f.write(s);
        f.close();
    }

    private void createFile() {
        try {
            File myObj = new File("infosurpatient.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void ajouter(ActionEvent actionEvent) throws IOException {
        createFile();
        writeInFile();

        PatientDaoImp patientDao = new PatientDaoImp();
        String uniqueId = UUID.randomUUID().toString();

        // Création du patient
        Patient newPatient = new Patient(
                uniqueId,
                nom.getText(),
                prenom.getText(),
                Integer.parseInt(age.getText()),
                genre.getText(),
                email.getText(),
                adresse.getText(),
                num.getText()
        );

        // Récupérer les tests sélectionnés
        List<TestLab> selectedTests = availableTests.stream()
                .filter(test -> test.getSelectedProperty().get())
                .collect(Collectors.toList());

        // Enregistrer le patient et ses tests
        patientDao.save(newPatient, selectedTests);
    }

    public void retur(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void barcode(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/GenererLireBarcode.fxml."));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
