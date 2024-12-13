package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginController {
    private static final Logger LOGGER = Logger.getLogger(loginController.class.getName());

    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label welcomeText;
    /**
     * Handles user login authentication
     * @param actionEvent The event triggered by login button
     */
    @FXML
    public void enter(ActionEvent actionEvent) {
        String username = loginField.getText().trim();
        String password = passwordField.getText();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }

        try {
            if (authenticateUser(username, password)) {
                navigateToAdminView(actionEvent);
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Login error", e);
            showAlert("Error", "An unexpected error occurred. Please try again.");
        }
    }

    /**
     * Authenticates user against the database
     * @param username The username entered
     * @param password The password entered
     * @return true if authentication is successful, false otherwise
     */
    private boolean authenticateUser(String username, String password) {
        return (Objects.equals(username, "admin") && Objects.equals(password, "admin"));
    }

    /**
     * Navigates to the admin view after successful login
     * @param actionEvent The event that triggered navigation
     * @throws IOException If FXML loading fails
     */
    private void navigateToAdminView(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = getClass().getResource("/ma/ensa/lis/STYLE.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigates to the registration view
     * @param actionEvent The mouse event that triggered navigation
     * @throws IOException If FXML loading fails
     */
    @FXML
    public void register(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/Registration.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = getClass().getResource("/ma/ensa/lis/STYLE.css").toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("User Registration");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Displays an alert dialog with the given title and message
     * @param title The title of the alert
     * @param message The message to display
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Optional method for welcome text (can be removed if not needed)
     */
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}