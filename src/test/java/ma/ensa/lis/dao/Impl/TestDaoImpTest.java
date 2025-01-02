package ma.ensa.lis.dao.Impl;

import ma.ensa.lis.Dao.Impl.TestDaoImp;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestDaoImpTest {

    private DbConnection dbConnectionMock;
    private TestDaoImp testDao;
    private Connection connectionMock;

    @BeforeEach
    public void setUp() throws SQLException {
        // Créez des mocks pour la connexion et les déclarations SQL
        dbConnectionMock = mock(DbConnection.class);
        connectionMock = mock(Connection.class);

        // Configurez le mock pour la méthode getConn() pour retourner une connexion simulée
        when(dbConnectionMock.getConn()).thenReturn(connectionMock);

        // Initialisez le DAO avec le mock
        testDao = new TestDaoImp(dbConnectionMock);
    }

    @Test
    public void testFindById() throws SQLException {
        // Préparer un ResultSet simulé
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getString("id")).thenReturn("8");
        when(resultSetMock.getString("name")).thenReturn("Protéine C réactive");
        when(resultSetMock.getString("category")).thenReturn("Immunologie");
        when(resultSetMock.getString("description")).thenReturn("Dosage pour évaluer les niveaux de CRP dans le sang");

        // Simuler la méthode executeQuery pour retourner le ResultSet simulé
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode findById
        TestLab test = testDao.findById("8");

        // Vérification des résultats
        assertNotNull(test);
        assertEquals("8", test.getId());
        assertEquals("Protéine C réactive", test.getName());
        assertEquals("Immunologie", test.getCategory());
        assertEquals("Dosage pour évaluer les niveaux de CRP dans le sang", test.getDescription());
    }

    @Test
    public void testFindAll() throws SQLException {
        // Préparer un ResultSet simulé
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);  // Une seule ligne
        when(resultSetMock.getString("id")).thenReturn("8");
        when(resultSetMock.getString("name")).thenReturn("Protéine C réactive");
        when(resultSetMock.getString("category")).thenReturn("Immunologie");
        when(resultSetMock.getString("description")).thenReturn("Dosage pour évaluer les niveaux de CRP dans le sang");

        // Simuler la méthode executeQuery pour retourner le ResultSet simulé
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode findAll
        List<TestLab> tests = testDao.findAll();

        // Vérification des résultats
        assertNotNull(tests);
        assertEquals(1, tests.size());
        TestLab test = tests.getFirst();
        assertEquals("8", test.getId());
        assertEquals("Protéine C réactive", test.getName());
        assertEquals("Immunologie", test.getCategory());
        assertEquals("Dosage pour évaluer les niveaux de CRP dans le sang", test.getDescription());
    }

    @Test
    public void testSave() throws SQLException {
        TestLab testLab = new TestLab();
        testLab.setId("8");
        testLab.setName("Protéine C réactive");
        testLab.setCategory("Immunologie");
        testLab.setDescription("Dosage pour évaluer les niveaux de CRP dans le sang");

        // Simuler la méthode executeUpdate pour l'insertion dans la base
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode save
        testDao.save(testLab);

        // Vérifier que la méthode executeUpdate a été appelée
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    public void testUpdate() throws SQLException {
        TestLab testLab = new TestLab();
        testLab.setId("8");
        testLab.setName("Protéine C réactive");
        testLab.setCategory("Immunologie");
        testLab.setDescription("Mise à jour du dosage de CRP");

        // Simuler la méthode executeUpdate pour la mise à jour dans la base
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode update
        testDao.update(testLab);

        // Vérifier que la méthode executeUpdate a été appelée
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        // Simuler la méthode executeUpdate pour la suppression dans la base
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode delete
        testDao.delete("8");

        // Vérifier que la méthode executeUpdate a été appelée
        verify(preparedStatementMock, times(1)).executeUpdate();
    }
}
