package ma.ensa.lis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends  Application {
    @Override
    public void start(Stage stage) throws IOException {

//        String schemaFilePath = "src/main/resources/schema.sql";
//        // Execute the schema file
//        try{
//            DbConnection db=new DbConnection();
//            Connection conn=db.getConn();
//            executeSqlScript(conn, schemaFilePath);
//            System.out.println("Database schema initialized successfully");
//        } catch (Exception e) {
//            System.out.println("An error occurred while connecting to the database");
//            e.printStackTrace();
//        }


        FXMLLoader fxmlLoader=new FXMLLoader(App.class.getResource("login-view.fxml"));

        Scene scene = new Scene((Parent) fxmlLoader.load(), 754, 622);
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