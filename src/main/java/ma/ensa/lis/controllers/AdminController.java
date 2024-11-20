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
import ma.ensa.lis.models.User;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Objects;

public class AdminController {


    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User,String> loginn;
    @FXML
    private TableColumn<User,String> pass;
    @FXML
    private TableColumn<User, Date> date_ns;
    @FXML
    private TableColumn<User, String> prenom;

    @FXML
    private TableColumn<User,String> name;
    @FXML
    private TableColumn<User,Integer> id;
    @FXML
    private TableColumn<User,String> gender;//liaison m3a table f view




    @FXML
    public void initialize () {//liaison m3a Patient  ,kola column mn tableau khass t3ref ina attribut f Patient takhod
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
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

        String sql2 = "SELECT * FROM Patient";
        ResultSet rs=stmt.executeQuery(sql2);
        ObservableList<User> ob= FXCollections.observableArrayList();
        while (rs.next()) {
            String id = rs.getString("id");
            String first_name = rs.getString("name");
            String login = rs.getString("login");
            String prenomm=rs.getString("last_name");
            String passe = rs.getString("password");
            Date date_ns = rs.getDate("date_ns");
            String gender = rs.getString("gender");
            User pa = new User(id,first_name,prenomm,gender);
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
