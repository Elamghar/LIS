package ma.ensa.lis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.useFullFunction;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class App extends  Application {
    @Override
    public void start(Stage stage) throws IOException {
        String schemaFilePath = "src/main/resources/schema.sql";
        DbConnection db=new DbConnection();
       //Execute the schema file
        try (Connection conn = db.getConn()) {
            useFullFunction.executeSqlScript(conn, schemaFilePath);
            System.out.println("Schema executed successfully.");
        } catch (IOException | SQLException e) {
            useFullFunction.ShowAlert("Schema Execution Error", e.getMessage());
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("login.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setResizable(false);
        stage.setTitle("LIS");
        Image logo = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/logo.png")));
        stage.getIcons().add(logo);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}