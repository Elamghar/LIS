//package ma.ensa.lis.controllers;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.scene.control.Alert;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.Button;
//import javafx.event.ActionEvent;
//import ma.ensa.lis.Dao.Impl.TestDaoImp;
//import ma.ensa.lis.models.TestLab;
//import ma.ensa.lis.utils.DbConnection;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class TestControllerTest {
//
//    private TestController testController;
//    private TestDaoImp mockTestDao;
//    private DbConnection mockDbConnection;
//    private TableView<TestLab> mockTestTable;
//    private ObservableList<TestLab> mockTestList;
//    private Alert mockAlert;
//
//    @BeforeEach
//    void setUp() {
//        // Mock the DbConnection to simulate database interaction
//        mockDbConnection = mock(DbConnection.class);
//        mockTestDao = new TestDaoImp(mockDbConnection);
//
//        // Mock JavaFX components
//        mockTestTable = mock(TableView.class);
//        mockTestList = FXCollections.observableArrayList();
//        mockAlert = mock(Alert.class);
//
//        // Create an instance of TestController with mocked dependencies
//        testController = new TestController() {
//            @Override
//            public TestDaoImp getTestDao() {
//                return mockTestDao;
//            }
//
//            @Override
//            public TableView<TestLab> getTestTable() {
//                return mockTestTable;
//            }
//
//            @Override
//            public ObservableList<TestLab> getTestList() {
//                return mockTestList;
//            }
//
//            @Override
//            public void showAlert(String title, String message) {
//                mockAlert.setContentText(message);  // Capture alert content
//            }
//        };
//
//        // Initialize the controller
//        testController.initialize();
//    }
//
//    @Test
//    void testCreateTest() {
//        // Arrange
//        when(mockTestDao.save(any(TestLab.class))).thenReturn(null);
//
//        // Simulate the createTest method
//        ActionEvent event = mock(ActionEvent.class);
//        testController.createTest(event);
//
//        // Assert
//        assertEquals(1, mockTestList.size()); // One new test should be added
//        verify(mockTestDao).save(any(TestLab.class)); // Save method is called
//        assertNotNull(mockAlert.getContentText());
//        assertTrue(mockAlert.getContentText().contains("Test Created"));
//    }
//
//    @Test
//    void testUpdateTest() {
//        // Arrange
//        TestLab test = new TestLab("Test1", "Category1", new java.util.Date(), "Description");
//        mockTestList.add(test);
//        when(mockTestDao.update(any(TestLab.class))).thenReturn(null);
//
//        // Simulate the updateTest method
//        ActionEvent event = mock(ActionEvent.class);
//        testController.updateTest(event);
//
//        // Assert
//        assertTrue(test.getName().contains("Updated")); // Name should be updated
//        verify(mockTestDao).update(test); // Update method is called
//        assertNotNull(mockAlert.getContentText());
//        assertTrue(mockAlert.getContentText().contains("Test Updated"));
//    }
//
//    @Test
//    void testDeleteTest() {
//        // Arrange
//        TestLab test = new TestLab("Test1", "Category1", new java.util.Date(), "Description");
//        mockTestList.add(test);
//        when(mockTestDao.delete(anyString())).thenReturn(null);
//
//        // Simulate the deleteTest method
//        ActionEvent event = mock(ActionEvent.class);
//        testController.deleteTest(event);
//
//        // Assert
//        assertTrue(mockTestList.isEmpty()); // Test should be removed from the list
//        verify(mockTestDao).delete(test.getId()); // Delete method is called
//        assertNotNull(mockAlert.getContentText());
//        assertTrue(mockAlert.getContentText().contains("Test Deleted"));
//    }
//
//    @Test
//    void testShowAlert() {
//        // Test the showAlert method by directly invoking it
//        testController.showAlert("Test", "Test alert");
//        verify(mockAlert).setContentText("Test alert");
//    }
//}
