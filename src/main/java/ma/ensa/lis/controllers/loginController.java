package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

import static ma.ensa.lis.utils.useFullFunction.ShowAlert;

public class loginController {

    @FXML
    TextField login;
    @FXML
    PasswordField ps;
    @FXML
    private Label welcomeText;

    @FXML
    public void enter(ActionEvent actionEvent) {
        String username = login.getText().trim();
        String password = ps.getText();
        if (username.isEmpty() || password.isEmpty()) {
            ShowAlert("Login Error", "Please enter both username and password.");
            return;
        }

        try {
            if (authenticateUser(username, password)) {
                navigateToAdminView(actionEvent);
            } else {
               ShowAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    boolean authenticateUser(String username, String password) {
        return (Objects.equals(username, "admin") && Objects.equals(password, "admin"));
    }
    void navigateToAdminView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = getClass().getResource("/ma/ensa/lis/admin.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }


}
