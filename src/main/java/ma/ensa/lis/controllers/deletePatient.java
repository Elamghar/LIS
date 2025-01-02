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

public class deletePatient {

    @FXML
    TextField CIN;
    @FXML
    TextField firstname;
    @FXML
    public void deletePatient(ActionEvent actionEvent) {
        PatientDaoImp pa=new PatientDaoImp();
        pa.delete(new Patient(firstname.getText(),CIN.getText()));
    }

    public void Return(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 620, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }
}
