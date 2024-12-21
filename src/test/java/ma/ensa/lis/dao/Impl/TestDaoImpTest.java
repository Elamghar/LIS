//package ma.ensa.lis.dao.Impl;
//
//import static javafx.beans.binding.Bindings.when;
//import static javax.management.Query.times;
//import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//import javafx.beans.value.ObservableBooleanValue;
//import ma.ensa.lis.Dao.Impl.TestDaoImp;
//import ma.ensa.lis.models.TestLab;
//import ma.ensa.lis.utils.DbConnection;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.sql.*;
//import java.util.List;
//
//class TestDaoImpTest {
//
//    private DbConnection dbConnection;
//    private TestDaoImp testDao;
//    private Connection mockConnection;
//
//    @BeforeEach
//    void setUp() {
//        mockConnection = mock(Connection.class);
//        dbConnection = mock(DbConnection.class);
//        when((ObservableBooleanValue) dbConnection.getConn()).then(mockConnection);
//        testDao = new TestDaoImp(dbConnection);
//    }
//
//    @Test
//    void testSave() throws SQLException {
//        TestLab test = new TestLab("Test1", "Category1", "Description");
//        String query = "INSERT INTO Test (id, name, category, testDate, expectedCompletionDate, status, result, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//        when((ObservableBooleanValue) mockConnection.prepareStatement(query)).then(mockStatement);
//
//        testDao.save(test);
//        verify(mockStatement, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testFindById() throws SQLException {
//        String testId = "123";
//        String query = "SELECT * FROM Test WHERE id = ?";
//        ResultSet mockResultSet = mock(ResultSet.class);
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//
//        when(mockConnection.prepareStatement(query)).thenReturn(mockStatement);
//        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true);
//        when(mockResultSet.getString("id")).thenReturn(testId);
//
//        TestLab test = testDao.findById(testId);
//
//        assertNotNull(test);
//        assertEquals(testId, test.getId());
//    }
//
//    @Test
//    void testFindAll() throws SQLException {
//        String query = "SELECT * FROM Test";
//        ResultSet mockResultSet = mock(ResultSet.class);
//        Statement mockStatement = mock(Statement.class);
//
//        when(mockConnection.createStatement()).thenReturn(mockStatement);
//        when(mockStatement.executeQuery(query)).thenReturn(mockResultSet);
//        when(mockResultSet.next()).thenReturn(true).thenReturn(false);  // Simulating one result
//        when(mockResultSet.getString("id")).thenReturn("123");
//
//        List<TestLab> tests = testDao.findAll();
//        assertNotNull(tests);
//        assertEquals(1, tests.size());
//    }
//
//    @Test
//    void testUpdate() throws SQLException {
//        TestLab test = new TestLab("Test1", "Category1", new Date(), "Description");
//        String query = "UPDATE Test SET name = ?, category = ?, testDate = ?, expectedCompletionDate = ?, status = ?, result = ?, price = ? WHERE id = ?";
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//        when(mockConnection.prepareStatement(query)).thenReturn(mockStatement);
//
//        testDao.update(test);
//        verify(mockStatement, times(1)).executeUpdate();
//    }
//
//    @Test
//    void testDelete() throws SQLException {
//        String testId = "123";
//        String query = "DELETE FROM Test WHERE id = ?";
//        PreparedStatement mockStatement = mock(PreparedStatement.class);
//        when(mockConnection.prepareStatement(query)).thenReturn(mockStatement);
//
//        testDao.delete(testId);
//        verify(mockStatement, times(1)).executeUpdate();
//    }
//}
//
