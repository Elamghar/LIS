package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.Dao.Impl.VisitDaoImp;
import ma.ensa.lis.models.Visit;
import ma.ensa.lis.utils.DbConnection;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientController {
    @FXML
    private TextField diag;
    @FXML
    private DatePicker datev;
    @FXML
    private DatePicker date_test;
    @FXML
    private TextField test_name;
    @FXML
    private TextField catego;
    @FXML
    private DatePicker date_compili;
    @FXML
    private TextField status;
    public void logout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/login-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void ajout_visit(ActionEvent actionEvent) throws IOException{
        VisitDaoImp visitcont=new VisitDaoImp();
        String uniqueId = UUID.randomUUID().toString();
        LocalDate localDate=datev.getValue(); //return localDate
        visitcont.save(new Visit(uniqueId,Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()), diag.getText()));//mn localDate l Date
    }
    public void ajout_test(ActionEvent actionEvent) throws IOException{
        TestDaoImp man=new TestDaoImp(new DbConnection());



    }
    public void medicalfile(ActionEvent actionEvent) throws IOException{
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
