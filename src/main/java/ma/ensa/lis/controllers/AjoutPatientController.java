package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.models.Patient;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

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
    private TextField role;
    @FXML
    private TextField adresse;

    public void ajouter(ActionEvent actionEvent) {
        PatientDaoImp patientdao =new PatientDaoImp();
        String uniqueId = UUID.randomUUID().toString();
        System.out.println(nom.getText()+"hhh");
        patientdao.save(new Patient(uniqueId,nom.getText(),prenom.getText(),Integer.parseInt(age.getText()),genre.getText(),email.getText(),adresse.getText(),role.getText(),num.getText()));
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
    }


