package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.models.Patient;

public class deletePatient {

    @FXML
    TextField email;//make the email insertionn unique
    @FXML
    public void delete(ActionEvent actionEvent) {
        PatientDaoImp pa=new PatientDaoImp();
        pa.delete(new Patient(email.getText()));
    }
}
