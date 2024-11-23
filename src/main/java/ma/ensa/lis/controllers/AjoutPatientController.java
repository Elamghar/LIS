package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.models.Patient;

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
}
