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
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.EmailSender;
import ma.ensa.lis.utils.PDFGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
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
            //showAlert("user not found","there is no patient with this email");
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
                String name = rs.getString("testName");
                String cat = rs.getString("category");
                String desc = rs.getString("desc");
                TestLab te = new TestLab(name, cat,desc);
                ob.add(te);
                table.setItems(ob);
            }
        }
    }

    private void writeInFile(String name,String nomTest,String category,Date date,String testresult) throws IOException {
        FileWriter f = new FileWriter("infosurpatient.txt");
        String s = name +","+nomTest + "," + category + "," + date+","+testresult;
        f.write(s);
        f.close();
    }

    private void createFile() {
        try {
            File myObj = new File("infosurtestdupatient.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public void generateFile(ActionEvent actionEvent) throws SQLException, IOException {
        String id=findId(email.getText());
        if(id!=null) {
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM Test WHERE patientId=?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            PatientDaoImp patientDaoImp=new PatientDaoImp();
            Patient patient=patientDaoImp.searchById(id);
            createFile();
            while (rs.next()) {
                String namee = rs.getString("testName");
                String diagg = rs.getString("category");
                Date datee = rs.getDate("dateTest");
                String resu = rs.getString("testResult");
                writeInFile(patient.getFirstName(),namee,diagg,datee,resu);
            }
        }
    }
    List<String> getdataForpdf() throws SQLException {
        String id = findId(email.getText());
        List<String> list = null;
        if (id != null) {
            list = new ArrayList<>();
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM Test WHERE patientId=?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            PatientDaoImp patientDaoImp = new PatientDaoImp();
            Patient patient = patientDaoImp.searchById(id);
            createFile();
            while (rs.next()) {
                String namee = rs.getString("testName");
                String diagg = rs.getString("category");
                String resu = rs.getString("testResult");
                list.add(namee);list.add(diagg);list.add(resu);
            }
        }
        else{
            System.out.println("this patient is not in the data base");
        }
        return list;
    }
    void makePdf() throws SQLException {
        String fileP="output.pdf";
        String cont="LABORATORY INFORMATION SYSTEM.";
        List<String> list=getdataForpdf();
        Patient pa=(new PatientDaoImp()).searchById(findId(email.getText()));
        if(pa==null) {
            System.out.println("this patient is not in the data base");
            return;
        }
        PDFGenerator pdfGenerator = new PDFGenerator(fileP, cont, list, pa.getFirstName());
        if (pdfGenerator.getPdfDocument()!=null) {
            System.out.println("PDF généré avec succès !");
        } else {
            System.out.println("Une erreur est survenue lors de la génération du PDF.");
        }
    }

    public void sendPdf(ActionEvent actionEvent) throws SQLException {
          makePdf();
        EmailSender emailSender=new EmailSender();
        emailSender.sendemail(email.getText());
    }
}

