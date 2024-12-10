package ma.ensa.lis.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import ma.ensa.lis.utils.DbConnection;

import javax.swing.*;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;
public class loginController {
    @FXML
    private TextField login;
    @FXML
    private PasswordField ps;
    @FXML
    private Label welcomeText;



    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }



    public void enter(javafx.event.ActionEvent actionEvent) throws IOException {
        try {
            DbConnection db=new DbConnection();
            Connection conn=db.getConn();
            Statement stmt=conn.createStatement();
            System.out.println("Connected to database");
            String lo=login.getText();
            String p=ps.getText();
            String sql = "SELECT * FROM Patient WHERE login='" + lo + "' AND passwd='" + p + "'";

            ResultSet rs=stmt.executeQuery(sql);
//            if(rs.next()){
//                System.out.println(rs.getString("login"));
//            }
            if(Objects.equals(lo, "hh") && Objects.equals(p, "hh")){
                System.out.println("hii");
                if(rs.next()) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    Scene scene = new Scene(fxmlLoader.load(), 754, 622);
                    String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
                    scene.getStylesheets().add(css);
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    JOptionPane.showMessageDialog(null,"ghletyyy a m3elem");
                    System.out.println(login.getText());
                    System.out.println(ps.getText());
                }
            }else{
                if(rs.next()) {

                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/patient-view.fxml"));
                    Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                    Scene scene = new Scene(fxmlLoader.load(), 754, 753);
                    String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
                    scene.getStylesheets().add(css);
                    stage.setTitle("Hello!");
                    stage.setScene(scene);
                    stage.show();
                }
                else{
                    JOptionPane.showMessageDialog(null,"ghletyyy a m3elem");
                    System.out.println(login.getText());
                    System.out.println(ps.getText());
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void register(MouseEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/registration.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}