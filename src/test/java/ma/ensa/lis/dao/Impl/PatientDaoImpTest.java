package ma.ensa.lis.dao.Impl;

import ma.ensa.lis.Dao.Impl.PatientDaoImp;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PatientDaoImpTest {

    private DbConnection dbConnectionMock;
    private PatientDaoImp patientDao;
    private Connection connectionMock;

    @BeforeEach
    public void setUp() throws SQLException {
        // Créez des mocks pour la connexion et les déclarations SQL
        dbConnectionMock = mock(DbConnection.class);
        connectionMock = mock(Connection.class);

        // Configurez le mock pour la méthode getConn() pour retourner une connexion simulée
        when(dbConnectionMock.getConn()).thenReturn(connectionMock);

        // Initialisez le DAO avec le mock
        patientDao = new PatientDaoImp();
    }

    @Test
    public void testSave() throws SQLException {
        // Préparez un patient avec les données modifiées
        Patient patient = new Patient("IA6871", "Mohammed", "Zian", 30, "Homme", "moh.zia30@example.com", "Marrakech, Daoudiat 765Z", "0720950271");

        // Préparez un test avec les nouvelles données
        TestLab testLab = new TestLab("21", "Créatinine", "Néphrologie", "Évaluation de la fonction rénale.");
        List<TestLab> tests = List.of(testLab);

        // Simuler la méthode executeUpdate pour l'insertion dans la base
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode save
        patientDao.save(patient, tests);

        // Vérifier que la méthode executeUpdate a été appelée pour les deux requêtes (Patient et Patient_test)
        verify(preparedStatementMock, times(2)).executeUpdate();
    }

    @Test
    public void testDelete() throws SQLException {
        // Préparez un patient à supprimer
        Patient patient = new Patient("IA6871", "Mohammed", "Zian", 30, "Homme", "moh.zia30@example.com", "Marrakech, Daoudiat 765Z", "0720950271");

        // Simuler la méthode executeUpdate pour la suppression dans la base
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode delete
        patientDao.delete(patient);

        // Vérifier que la méthode executeUpdate a été appelée pour la suppression
        verify(preparedStatementMock, times(1)).executeUpdate();
    }

    @Test
    public void testSearchByCIN() throws SQLException {
        // Simuler le résultat de la base de données
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getString("CIN")).thenReturn("IA6871");
        when(resultSetMock.getString("firstName")).thenReturn("Mohammed");
        when(resultSetMock.getString("lastName")).thenReturn("Zian");
        when(resultSetMock.getInt("age")).thenReturn(30);
        when(resultSetMock.getString("gender")).thenReturn("Homme");
        when(resultSetMock.getString("email")).thenReturn("moh.zia30@example.com");
        when(resultSetMock.getString("address")).thenReturn("Marrakech, Daoudiat 765Z");

        // Simuler la méthode executeQuery pour retourner le ResultSet simulé
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode searchByCIN
        Patient patient = patientDao.searchByCIN("IA6871");

        // Vérification des résultats
        assertNotNull(patient);
        assertEquals("IA6871", patient.getCIN());
        assertEquals("Mohammed", patient.getFirstName());
        assertEquals("Zian", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Homme", patient.getGender());
        assertEquals("moh.zia30@example.com", patient.getEmail());
        assertEquals("Marrakech, Daoudiat 765Z", patient.getAddress());
    }

    @Test
    public void testSearchByFirstName() throws SQLException {
        // Simuler le résultat de la base de données
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);  // Une seule ligne
        when(resultSetMock.getString("CIN")).thenReturn("IA6871");
        when(resultSetMock.getString("firstName")).thenReturn("Mohammed");
        when(resultSetMock.getString("lastName")).thenReturn("Zian");
        when(resultSetMock.getInt("age")).thenReturn(30);
        when(resultSetMock.getString("gender")).thenReturn("Homme");
        when(resultSetMock.getString("email")).thenReturn("moh.zia30@example.com");
        when(resultSetMock.getString("address")).thenReturn("Marrakech, Daoudiat 765Z");

        // Simuler la méthode executeQuery pour retourner le ResultSet simulé
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode searchByFirstName
        List<Patient> patients = patientDao.searchByFirstName("Mohammed");

        // Vérification des résultats
        assertNotNull(patients);
        assertEquals(1, patients.size());
        Patient patient = patients.getFirst();
        assertEquals("IA6871", patient.getCIN());
        assertEquals("Mohammed", patient.getFirstName());
        assertEquals("Zian", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Homme", patient.getGender());
        assertEquals("moh.zia30@example.com", patient.getEmail());
        assertEquals("Marrakech, Daoudiat 765Z", patient.getAddress());
    }

    @Test
    public void testGetAllPatients() throws SQLException {
        // Simuler le résultat de la base de données
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true).thenReturn(false);  // Une seule ligne
        when(resultSetMock.getString("CIN")).thenReturn("IA6871");
        when(resultSetMock.getString("firstName")).thenReturn("Mohammed");
        when(resultSetMock.getString("lastName")).thenReturn("Zian");
        when(resultSetMock.getInt("age")).thenReturn(30);
        when(resultSetMock.getString("gender")).thenReturn("Homme");
        when(resultSetMock.getString("email")).thenReturn("moh.zia30@example.com");
        when(resultSetMock.getString("address")).thenReturn("Marrakech, Daoudiat 765Z");

        // Simuler la méthode executeQuery pour retourner le ResultSet simulé
        PreparedStatement preparedStatementMock = mock(PreparedStatement.class);
        when(preparedStatementMock.executeQuery()).thenReturn(resultSetMock);
        when(connectionMock.prepareStatement(anyString())).thenReturn(preparedStatementMock);

        // Appel à la méthode getAllPatients
        List<Patient> patients = patientDao.getAllPatients();

        // Vérification des résultats
        assertNotNull(patients);
        assertEquals(1, patients.size());
        Patient patient = patients.get(0);
        assertEquals("IA6871", patient.getCIN());
        assertEquals("Mohammed", patient.getFirstName());
        assertEquals("Zian", patient.getLastName());
        assertEquals(30, patient.getAge());
        assertEquals("Homme", patient.getGender());
        assertEquals("moh.zia30@example.com", patient.getEmail());
        assertEquals("Marrakech, Daoudiat 765Z", patient.getAddress());
    }
}
