package ma.ensa.lis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Objects;

import static ma.ensa.lis.utils.useFullFunction.showAlert;

public class MedicalfileController {
    @FXML
    private TableView<TestLab> table;
    @FXML
    private TableColumn<TestLab,String> name;
    @FXML
    private TableColumn<TestLab,String> diag;
    @FXML
    private TableColumn<TestLab, Date> date;
    @FXML
    private TableColumn<TestLab,String> result;
    @FXML
    private TextField email;
    @FXML
    public void initialize () {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        diag.setCellValueFactory(new PropertyValueFactory<>("category"));
        date.setCellValueFactory(new PropertyValueFactory<>("testDate"));
        result.setCellValueFactory(new PropertyValueFactory<>("result"));
    }

    @FXML
    public void returne(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 754, 622);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/STYLE.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    String findId(String email) throws SQLException {
        DbConnection db=new DbConnection();
        Connection connection=db.getConn();
        String sql="SELECT * FROM patient WHERE email = ?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1, email);
        ResultSet rs=stmt.executeQuery();
        if (rs.next()) {
            String id=rs.getString("patientId");
            System.out.println(id);
            return id;
        } else {
            showAlert("user not found","there is no patient with this email");
            return null;
        }
    }

    @FXML
    public void seee(ActionEvent actionEvent) throws SQLException {
        String id=findId(email.getText());
        if(id!=null) {
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM Test WHERE patientId=?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            ObservableList<TestLab> ob = FXCollections.observableArrayList();
            while (rs.next()) {
                String namee = rs.getString("testName");
                String diagg = rs.getString("category");
                Date datee = rs.getDate("dateTest");
                String resu = rs.getString("testResult");
                TestLab te = new TestLab(namee, diagg, datee, resu);
                ob.add(te);
                table.setItems(ob);
            }
        }
    }


}

