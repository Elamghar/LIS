package ma.ensa.lis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ma.ensa.lis.models.Patient;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class AdminController {


    @FXML
    private TableView<Patient> table;
    @FXML
    private TableColumn<Patient,String> loginn;
    @FXML
    private TableColumn<Patient,String> pass;
    @FXML
    private TableColumn<Patient, Date> date_ns;
    @FXML
    private TableColumn<Patient,String> name;
    @FXML
    private TableColumn<Patient,Integer> id;
    @FXML
    private TableColumn<Patient,String> country;//liaison m3a table f view




    @FXML
    public void initialize () {//liaison m3a Patient  ,kola column mn tableau khass t3ref ina attribut f Patient takhod
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        loginn.setCellValueFactory(new PropertyValueFactory<>("login"));
        date_ns.setCellValueFactory(new PropertyValueFactory<>("date_ns"));
        pass.setCellValueFactory(new PropertyValueFactory<>("pass"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
    }


    String namee="root";
    String passs="root";



    String n = "root";
    String p = "root";
    String url = "jdbc:mysql://localhost:3306/data1?useSSL=false&serverTimezone=UTC";

    public void gh(javafx.event.ActionEvent actionEvent) throws SQLException {
        System.out.println("saf ghyerha");
        Connection connection = DriverManager.getConnection(url, n, p);
        Statement stmt = connection.createStatement();

        String sql2 = "SELECT * FROM reg";
        ResultSet rs=stmt.executeQuery(sql2);
        ObservableList<Patient> ob= FXCollections.observableArrayList();
        while (rs.next()) {
            String id = rs.getString("id");
            String namee = rs.getString("fullname");
            String login = rs.getString("login");

            String passe = rs.getString("password");
            Date date_ns = rs.getDate("date_ns");
            String country = rs.getString("country");
            Patient pa = new Patient(id,namee,namee);
            ob.add(pa);
            table.setItems(ob);
        }

    }

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
}
