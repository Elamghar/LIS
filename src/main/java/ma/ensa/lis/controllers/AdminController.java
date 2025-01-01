package ma.ensa.lis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.utils.DbConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TableView<Patient> table;
    @FXML
    private TableColumn<Patient, String> id; // Changer le type de la colonne à String
    @FXML
    private TableColumn<Patient, String> name;
    @FXML
    private TableColumn<Patient, String> prenom;
    @FXML
    private TableColumn<Patient, String> gender;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Associer les colonnes aux propriétés de l'objet Patient
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        try {
            loadPatientDetails(); // Charger les patients depuis la base de données
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour charger les patients depuis la base de données
    public void loadPatientDetails() throws SQLException {
        DbConnection db = new DbConnection();
        Connection connection = db.getConn();
        Statement stmt = connection.createStatement();

        // Requête SQL pour récupérer les informations des patients
        String sql = "SELECT * FROM patient";
        ResultSet rs = stmt.executeQuery(sql);

        ObservableList<Patient> patients = FXCollections.observableArrayList();

        // Parcours des résultats et ajout à la liste observable
        while (rs.next()) {
            String patientId = rs.getString("patientId"); // ID est maintenant une String
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String gender = rs.getString("gender");

            // Création d'un objet Patient avec l'ID en String
            Patient patient = new Patient(patientId, firstName, lastName, gender);
            patients.add(patient);
        }

        // Associer les patients récupérés à la table
        table.setItems(patients);

        // Fermer la connexion à la base de données
        db.closeConnection();
    }

    // Méthodes pour naviguer entre les vues (login, ajout, suppression, etc.)
    public void logout(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/login-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void createPatient(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/AjoutPatient-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 600, 650);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void deletePatient(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/deletePatient-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void seeMedicalFile(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/medicalfile-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
