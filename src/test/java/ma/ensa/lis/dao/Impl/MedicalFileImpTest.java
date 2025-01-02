package ma.ensa.lis.dao.Impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import ma.ensa.lis.Dao.Impl.MedicalFileImp;
import ma.ensa.lis.models.MedicalFile;
import ma.ensa.lis.utils.DbConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class MedicalFileImpTest {

    @Mock
    private DbConnection mockDbConnection;
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private MedicalFileImp medicalFileDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        medicalFileDao = new MedicalFileImp();
    }

    @Test
    public void testSaveMed() throws SQLException, JsonProcessingException {
        // Préparation
        MedicalFile medicalFile = new MedicalFile();
        medicalFile.setDateCrea(new java.util.Date());
        medicalFile.setDateModif(new java.util.Date());
        medicalFile.setAllergies(List.of("Arachides", "Pénicilline"));
        medicalFile.setNotes(List.of("Le patient nécessite une surveillance"));

        when(mockDbConnection.getConn()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // Exécution
        medicalFileDao.saveMed(medicalFile);

        // Vérification
        verify(mockPreparedStatement).setDate(1, new java.sql.Date(medicalFile.getDateCrea().getTime()));
        verify(mockPreparedStatement).setDate(2, new java.sql.Date(medicalFile.getDateModif().getTime()));
        verify(mockPreparedStatement).setString(3, "{\"Arachides\",\"Pénicilline\"}");
        verify(mockPreparedStatement).setString(4, "{\"Le patient nécessite une surveillance\"}");
        verify(mockPreparedStatement).executeUpdate();
    }

    @Test
    public void testSearchMed() throws SQLException, IOException {
        // Préparation
        int medicalFileId = 1;
        String allergiesJson = "{\"Arachides\",\"Pénicilline\"}";
        String notesJson = "{\"Le patient nécessite une surveillance\"}";

        when(mockDbConnection.getConn()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(medicalFileId);
        when(mockResultSet.getString("allergies")).thenReturn(allergiesJson);
        when(mockResultSet.getString("Notes")).thenReturn(notesJson);
        when(mockResultSet.getDate("date_crea")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(mockResultSet.getDate("date_modif")).thenReturn(new java.sql.Date(System.currentTimeMillis()));

        // Exécution
        MedicalFile result = medicalFileDao.searchMed(medicalFileId);

        // Vérification
        assertNotNull(result);
        assertEquals(medicalFileId, result.getId());
        assertEquals(List.of("Arachides", "Pénicilline"), result.getAllergies());
        assertEquals(List.of("Le patient nécessite une surveillance"), result.getNotes());
    }
}
