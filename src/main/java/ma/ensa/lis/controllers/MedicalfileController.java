package ma.ensa.lis.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;
import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Patient_test;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.EmailSender;
import ma.ensa.lis.utils.PDFGenerator;
import ma.ensa.lis.utils.useFullFunction;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static ma.ensa.lis.utils.useFullFunction.ShowAlert;

@Getter
@Setter
public class MedicalfileController implements Initializable {
    @FXML
    private TableView<TestLab> testTableView;
    @FXML
    private TableColumn<TestLab, String> testIdCol;
    @FXML
    private TableColumn<TestLab, String> testNomCol;
    @FXML
    private TableColumn<TestLab, String> testCatCol;
    @FXML
    private TableColumn<TestLab, String> testDescCol;
    @FXML
    private TableColumn<TestLab, Boolean> testSelectCol;
    @FXML
    private TableView<Patient_test> table;
    @FXML
    private TableColumn<Patient_test,String> name;

    @FXML
    private TableColumn<Patient_test, Date> date;

    @FXML
    private TextField CINN;
    @FXML
    private final ObservableList<TestLab> availableTests = FXCollections.observableArrayList();
    boolean testExist=false;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<>("testname"));
        date.setCellValueFactory(new PropertyValueFactory<>("dateTest"));
        System.out.println("Initializing AjoutPatientController...");
        // Configure the table columns
        testIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        testNomCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        testCatCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));
        testDescCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        // Configure the checkbox column
        testSelectCol.setCellValueFactory(cellData -> cellData.getValue().getSelectedProperty());
        testSelectCol.setCellFactory(column -> new CheckBoxTableCell<>());

        // Make the table editable
        testTableView.setEditable(true);
        // Enable multiple selection
        testTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        // Load data into the table
        loadTestsFromDB();
        testTableView.setItems(availableTests);
    }

    @FXML
    public void AddTests(ActionEvent actionEvent) throws IOException {
        if(!testExist){
            ShowAlert("Patient Not Found","The patient You are looking For Does not exist, Try to add it");
        }
        else{
            //Save the changes to the patient test table
        }
    }

    @FXML
    public void returne(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ma/ensa/lis/admin-view.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(fxmlLoader.load(), 700, 600);
        String css = Objects.requireNonNull(this.getClass().getResource("/ma/ensa/lis/admin.css")).toExternalForm();
        scene.getStylesheets().add(css);
        stage.setTitle("LIS");
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void seee(ActionEvent actionEvent) throws SQLException {
        System.out.println("entered to seeDetails");
        String CIN=CINN.getText();
        System.out.println(CIN+"iwaa");
        if(CIN!=null) {
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM patient_test WHERE CIN = ?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, CIN.trim());
            ResultSet rs = stmt.executeQuery();
            System.out.println("dkhelt"+rs.next());
            ObservableList<Patient_test> ob = FXCollections.observableArrayList();
            while (rs.next()) {
                String name = rs.getString("testName");
                System.out.println(name);
                Date date = rs.getDate("dateTEST");
                System.out.println(date);
                Patient_test te = new Patient_test(name,date);
                ob.add(te);
                testExist=true;
            }
            table.setItems(ob);
        }else{
            useFullFunction.ShowAlert("user not found","there is no patient with this email");
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
        String CIN=CINN.getText();
        if(CIN!=null) {
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM Test WHERE CIN=?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, CIN);
            ResultSet rs = stmt.executeQuery();
            PatientDaoImp patientDaoImp=new PatientDaoImp();
            Patient patient=patientDaoImp.searchByCIN(CIN);
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
        String CIN = CINN.getText();
        List<String> list = null;
        if (CIN != null) {
            list = new ArrayList<>();
            DbConnection db = new DbConnection();
            Connection connection = db.getConn();
            String sql2 = "SELECT * FROM patient_test WHERE CIN=?";
            PreparedStatement stmt = connection.prepareStatement(sql2);
            stmt.setString(1, CIN);
            ResultSet rs = stmt.executeQuery();
            PatientDaoImp patientDaoImp = new PatientDaoImp();
            Patient patient = patientDaoImp.searchByCIN(CIN);
            createFile();
            while (rs.next()) {
                String namee = rs.getString("testName");
                String  date = rs.getString("datetest");
                list.add(namee);list.add(date);
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
        Patient pa=(new PatientDaoImp()).searchByCIN(CINN.getText());
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
        String email=new PatientDaoImp().findemail(CINN.getText());
        emailSender.sendemail(email);
    }


    public void loadTestsFromDB() {
        try {
            TestDaoImp testDao = new TestDaoImp(new DbConnection());
            availableTests.clear();
            List<TestLab> tests = testDao.findAll();
            // Initialize all tests as unselected
            tests.forEach(test -> test.setSelected(false));
            availableTests.addAll(tests);
            System.out.println("Loaded " + tests.size() + " tests from database");
        } catch (Exception e) {
            System.err.println("Error loading tests: " + e.getMessage());
            ShowAlert("Database Error", "Failed to load tests from database");
        }
    }

}

