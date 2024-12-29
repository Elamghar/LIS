package ma.ensa.lis.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.stage.Stage;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.stream.Collectors;

import static ma.ensa.lis.utils.useFullFunction.ShowAlert;

public class AjoutPatientController implements Initializable {
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField age;
    @FXML
    private TextField num;
    @FXML
    private TextField email;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<String> genderComboBox;

    @FXML
    private TableView<TestLab> testTableView;
    @FXML
    private TableColumn<TestLab, String> testIdCol;
    @FXML
    private TableColumn<TestLab, String> testNomCol;
    @FXML
    private TableColumn<TestLab, String> testCatCol;
    @FXML
    private TableColumn<TestLab, String> testDescCol;
    @FXML
    private TableColumn<TestLab, Boolean> testSelectCol;

    private final ObservableList<TestLab> availableTests = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        System.out.println("Initializing AjoutPatientController...");
        // Configure the table columns
        testIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        testNomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        testCatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        testDescCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        // Configure the checkbox column
        testSelectCol.setCellValueFactory(cellData -> cellData.getValue().getSelectedProperty());
        testSelectCol.setCellFactory(column -> new CheckBoxTableCell<>());

        // Make the table editable
        testTableView.setEditable(true);
        // Enable multiple selection
        testTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Load data into the table
        loadTestsFromDB();
        testTableView.setItems(availableTests);
        // Initialize gender ComboBox
        if (genderComboBox.getItems().isEmpty()){
            genderComboBox.getItems().addAll("Male", "Female");
        }

        // Add checkbox click handler
        testTableView.setOnMouseClicked(event -> {
            TestLab selectedTest = testTableView.getSelectionModel().getSelectedItem();
            if (selectedTest != null && event.getClickCount() == 1) {
                selectedTest.setSelected(!selectedTest.isSelected());
                testTableView.refresh();
                System.out.println("Test selected: " + selectedTest.getName() + " - " + selectedTest.isSelected());
            }
        });
    }

    private void loadTestsFromDB() {
        try {
            TestDaoImp testDao = new TestDaoImp(new DbConnection());
            availableTests.clear();
            List<TestLab> tests = testDao.findAll();
            // Initialize all tests as unselected
            tests.forEach(test -> test.setSelected(false));
            availableTests.addAll(tests);
            System.out.println("Loaded " + tests.size() + " tests from database");
        } catch (Exception e) {
            System.err.println("Error loading tests: " + e.getMessage());
            ShowAlert("Database Error", "Failed to load tests from database");
        }
    }
    @FXML
    public void ajouter(ActionEvent actionEvent) {
        try {
            if (!validateFields()){
                return;
            }
            createPatientFiles();
            savePatient();
            ShowAlert("Success", "Patient added successfully");
            clearForm();
        } catch (NumberFormatException e) {
            ShowAlert("Invalid Input", "Please enter a valid age");
        } catch (Exception e) {
            System.err.println("Error adding patient: " + e.getMessage());
            ShowAlert("Error", "Failed to add patient");
        }
    }

    private boolean validateFields(){
        if (nom.getText().isEmpty() || prenom.getText().isEmpty() || age.getText().isEmpty() ||
                num.getText().isEmpty() || email.getText().isEmpty() || adresse.getText().isEmpty() ||
                genderComboBox.getValue() == null) {
            ShowAlert("Validation Error", "All fields must be filled");
            return false;
        }
        try {
            Integer.parseInt(age.getText());
        } catch (NumberFormatException e) {
            ShowAlert("Invalid Age", "Please enter a valid number for age");
            return false;
        }

        return true;
    }

    private void createPatientFiles() throws IOException {
        // Create and write to patient info file
        createFile("infosurpatient.txt");
        writePatientInfo();
        // Create and write selected tests file
        List<TestLab> selectedTests = getSelectedTests();
        writeSelectedTests(selectedTests);
    }

    private void savePatient() {
        PatientDaoImp patientDao = new PatientDaoImp();
        String uniqueId = UUID.randomUUID().toString();

        Patient newPatient = new Patient(
                uniqueId,
                nom.getText(),
                prenom.getText(),
                Integer.parseInt(age.getText()),
                genderComboBox.getValue(),
                email.getText(),
                adresse.getText(),
                num.getText()
        );

        List<TestLab> selectedTests = getSelectedTests();
        patientDao.save(newPatient, selectedTests);
    }

    private void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else{
                System.out.println("File already exists: " + fileName);
            }
        } catch (IOException e) {
            System.err.println("Error creating file " + fileName + ": " + e.getMessage());
            throw new RuntimeException("Failed to create file: " + fileName, e);
        }
    }

    private void writePatientInfo() throws IOException {
        try (FileWriter writer = new FileWriter("infosurpatient.txt")) {
            String patientInfo = String.join(",",
                    nom.getText(),
                    prenom.getText(),
                    age.getText()
            );
            writer.write(patientInfo);
        }
    }

    private List<TestLab> getSelectedTests() {
        return availableTests.stream()
                .filter(TestLab::isSelected)
                .collect(Collectors.toList());
    }

    private void writeSelectedTests(List<TestLab> selectedTests) throws IOException {
        try (FileWriter writer = new FileWriter("selectedTests.txt")) {
            for (TestLab test : selectedTests) {
                writer.write(test.getId() + System.lineSeparator());
            }
        }
    }

    private void clearForm() {
        nom.clear();
        prenom.clear();
        age.clear();
        num.clear();
        email.clear();
        adresse.clear();
        genderComboBox.setValue(null);

        // Clear test selections
        availableTests.forEach(test -> test.setSelected(false));
        testTableView.refresh();
    }

    @FXML
    public void returnn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Admin Panel");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void accederbarcode(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/GenererBarcode.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Barcode Generator");
        stage.setScene(scene);
        stage.show();
    }
}