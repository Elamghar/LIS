package ma.ensa.lis.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;

import static ma.ensa.lis.utils.useFullFunction.ShowAlert;



public abstract class TestController {

    @FXML
    private TableView<TestLab> testTable;
    @FXML
    private TableColumn<TestLab, String> nameCol, categoryCol, descriptionCol;
    @FXML
    private TableColumn<TestLab, Boolean> selectCol;
    @FXML
    private Button createBtn, updateBtn, deleteBtn;

    private final TestDaoImp testDao;
    private final ObservableList<TestLab> testList;

    public TestController() {
        DbConnection dbConnection = new DbConnection();
        this.testDao = new TestDaoImp(dbConnection);
        this.testList = FXCollections.observableArrayList(testDao.findAll());
    }

    @FXML
    public void initialize() {
        setupTableColumns();
        loadTests();
    }

    private void setupTableColumns() {
        // Set up columns with bindings
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // CheckBox column for selection
        selectCol.setCellValueFactory(cellData -> cellData.getValue().getSelectedProperty());
        selectCol.setCellFactory(CheckBoxTableCell.forTableColumn(selectCol));

        // Bind the TableView with the ObservableList
        testTable.setItems(testList);
    }

    private void loadTests() {
        // Load all tests from the database
        testList.clear();
        testList.addAll(testDao.findAll());
    }

    @FXML
    void createTest(ActionEvent actionEvent) {
        String name = "New Test";
        String category = "Default Category";
        String description = "Default Description";

        // Create a new test
        TestLab newTest = new TestLab(generateUniqueId(), name, category, description, null);
        testDao.save(newTest);

        // Add the new test to the list and refresh the table
        testList.add(newTest);
        testTable.refresh();
        ShowAlert("Test Created", "A new test has been successfully created.");
    }

    @FXML
    void updateTest(ActionEvent actionEvent) {
        TestLab selectedTest = testTable.getSelectionModel().getSelectedItem();
        if (selectedTest == null) {
            ShowAlert("No Selection", "Please select a test to update.");
            return;
        }

        // Update the selected test in the database
        selectedTest.setName(selectedTest.getName() + " - Updated");
        selectedTest.setDescription("Updated Description");
        testDao.update(selectedTest);

        testTable.refresh();
        ShowAlert("Test Updated", "The selected test has been successfully updated.");
    }

    @FXML
    private void deleteTest(ActionEvent actionEvent) {
        TestLab selectedTest = testTable.getSelectionModel().getSelectedItem();
        if (selectedTest == null) {
            ShowAlert("No Selection", "Please select a test to delete.");
            return;
        }

        // Delete the selected test from the database
        testDao.delete(selectedTest.getId());
        testList.remove(selectedTest);
        testTable.refresh();
        ShowAlert("Test Deleted", "The selected test has been successfully deleted.");
    }

    @FXML
    private void logout(ActionEvent actionEvent) {
        // Logic for logging out (e.g., returning to login screen)
        System.out.println("Logged out");
    }

    private String generateUniqueId() {
        return "TST-" + System.currentTimeMillis();
    }

    protected abstract TestDaoImp getTestDao();

    protected abstract TableView<TestLab> getTestTable();

    protected abstract ObservableList<TestLab> getTestList();

//    private void ShowAlert(String title, String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }

    //protected abstract void ShowAlert(String title, String message); //khoya mafhemtch  ach kadir hna hhhhhhhhh
}
