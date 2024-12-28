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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

public class AjoutPatientController  implements Initializable {
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("je suis laaaaaaaaaaaaaaaaaaaaaaaaa");

        // Configure columns
        testIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        testNomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        testCatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        testDescCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        // Bind the checkbox column to the `selected` property
        testSelectCol.setCellValueFactory(cellData -> cellData.getValue().getSelectedProperty());
        testSelectCol.setCellFactory(CheckBoxTableCell.forTableColumn(testSelectCol));
        // Load data into the table
        loadTestsFromDB();
        testTableView.setItems(availableTests);
    }
    private void loadTestsFromDB() {
        TestDaoImp testDao = new TestDaoImp(new DbConnection());
        List<TestLab> tests = testDao.findAll();
        System.out.print("je suis dans looaaad"+tests);      // Récupère les tests depuis la BD
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

    public void returnn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void accederbarcode(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/GenererLireBarcode.fxml."));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 1000, 700);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
