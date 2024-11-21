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
import ma.ensa.lis.utils.DbConnection;

import java.io.IOException;
import java.util.Objects;

public class VisitController {
    @FXML
    private DatePicker visitdate ;
    @FXML
    private TextField diag;
    @FXML
    public void addVisit(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/addVisit-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Ajout d'une visite!");
        stage.setScene(scene);
        stage.show();
    }
    public void ajout_test(ActionEvent actionEvent) throws IOException{
        TestDaoImp man=new TestDaoImp(new DbConnection());
    }
}
