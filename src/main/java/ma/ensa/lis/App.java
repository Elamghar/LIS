package ma.ensa.lis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ma.ensa.lis.utils.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

import static ma.ensa.lis.utils.useFullFunction.executeSqlScript;

public class App extends  Application {
    @Override
    public void start(Stage stage) throws IOException {

        String schemaFilePath = "src/main/resources/schema.sql";
//         Execute the schema file
        try{
          DbConnection db=new DbConnection();
         Connection conn=db.getConn();
         executeSqlScript(conn, schemaFilePath);
         System.out.println("Database schema initializedaan successfully.");
        } catch (Exception e) {
          System.out.println("An error occurred while connecting to the database or initializing the schema.");
          e.printStackTrace();
        }
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("GenererLireBarcode.fxml"));

        Scene scene = new Scene((Parent) fxmlLoader.load(), 800, 700);

        String css = Objects.requireNonNull(this.getClass().getResource("STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}