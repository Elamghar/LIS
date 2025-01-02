package ma.ensa.lis.controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @Test
    void testAuthenticateUser_Success() throws Exception {
        // Initialisation du contrôleur
        loginController controller = new loginController();

        // Utilisation de la réflexion pour accéder à la méthode privée
        var authenticateUserMethod = loginController.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateUserMethod.setAccessible(true);

        // Tester avec des identifiants corrects
        boolean result = (boolean) authenticateUserMethod.invoke(controller, "admin", "admin");
        assertTrue(result, "L'authentification devrait réussir pour 'admin'/'admin'.");
    }

    @Test
    void testAuthenticateUser_Failure() throws Exception {
        // Initialisation du contrôleur
        loginController controller = new loginController();

        // Utilisation de la réflexion pour accéder à la méthode privée
        var authenticateUserMethod = loginController.class.getDeclaredMethod("authenticateUser", String.class, String.class);
        authenticateUserMethod.setAccessible(true);

        // Tester avec des identifiants incorrects
        boolean result = (boolean) authenticateUserMethod.invoke(controller, "not_admin", "sdfihjkef");
        assertFalse(result, "L'authentification devrait échouer pour 'not_admin'/'sdfihjkef'.");
    }

    @Test
    void testEnter_SuccessfulLogin() throws Exception {
        // Initialisation du contrôleur
        loginController controller = new loginController();

        // Simuler les champs de texte
        controller.login = mock(TextField.class);
        controller.ps = mock(PasswordField.class);

        when(controller.login.getText()).thenReturn("admin");
        when(controller.ps.getText()).thenReturn("admin");

        // Simuler un événement ActionEvent
        ActionEvent mockEvent = mock(ActionEvent.class);

        // Espionner la méthode navigateToAdminView
        loginController spyController = spy(controller);
        doNothing().when(spyController).navigateToAdminView(mockEvent);

        // Appeler la méthode `enter`
        spyController.enter(mockEvent);

        // Vérifier que la navigation a été appelée
        verify(spyController, times(1)).navigateToAdminView(mockEvent);
    }

    @Test
    void testEnter_FailedLogin() throws IOException {
        // Initialisation du contrôleur
        loginController controller = new loginController();

        // Simuler les champs de texte
        controller.login = mock(TextField.class);
        controller.ps = mock(PasswordField.class);

        when(controller.login.getText()).thenReturn("not_admin");
        when(controller.ps.getText()).thenReturn("sdfihjkef");

        // Simuler un événement ActionEvent
        ActionEvent mockEvent = mock(ActionEvent.class);

        // Espionner la méthode navigateToAdminView
        loginController spyController = spy(controller);
        doNothing().when(spyController).navigateToAdminView(mockEvent);

        // Appeler la méthode `enter`
        spyController.enter(mockEvent);

        // Vérifier que la navigation n'a pas été appelée
        verify(spyController, never()).navigateToAdminView(mockEvent);
    }
}
