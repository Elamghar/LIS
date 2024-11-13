package ma.ensa.lis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class RegisterController {
    @FXML
    private TextField fullness;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    private TextField country;

    @FXML
    private DatePicker age;

    public void login(MouseEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/login-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    public void register(javafx.event.ActionEvent actionEvent) throws IOException {
        if (login == null || fullness == null || password == null || country == null || age == null) {
            System.out.println("One or more fields are not initialized,enterrrrr alll of themm");
            JOptionPane.showMessageDialog(null,"you're so missed up ");
            return;
        }
        String url="jdbc:mysql://localhost:3306/data1?useSSL=false&serverTimezone=UTC";
        String name="root";
        String pass="root";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connect= DriverManager.getConnection(url, name, pass);
            Statement stmt=connect.createStatement();
            System.out.println("Connected to database");
//            System.out.println(fullness.getText());
//            System.out.println(password.getText());
//            System.out.println(login.getText());
//            System.out.println(country.getText());
//            System.out.println(age.getValue());
            String sql="insert into reg values('"+fullness.getText()+"','"+login.getText()+"','"+password.getText()+"','"+age.getValue()+"','"+country.getText()+"',null)";
            stmt.executeUpdate(sql);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/login-view.fxml"));
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(fxmlLoader.load(), 754, 622);
            String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
        catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}