package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private loginController loginController;
    private ActionEvent mockActionEvent;

    @BeforeEach
    void setUp() {
        loginController = new loginController();
        mockActionEvent = new ActionEvent();
    }

    @Test
    void testAuthenticateUser_ValidCredentials() {
        // Test avec des identifiants valides
        boolean result = loginController.authenticateUser("admin", "admin");
        assertTrue(result, "La connexion avec des informations valides devrait retourner true.");
    }

    @Test
    void testAuthenticateUser_InvalidCredentials() {
        // Test avec des identifiants invalides
        boolean result = loginController.authenticateUser("user", "password");
        assertFalse(result, "La connexion avec des informations invalides devrait retourner false.");
    }



}
