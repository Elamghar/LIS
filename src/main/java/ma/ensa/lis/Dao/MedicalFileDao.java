package ma.ensa.lis.Dao;

import ma.ensa.lis.models.MedicalFile;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Visit;

import java.io.File;
import java.util.List;

public interface MedicalFileDao {

    void saveMed(MedicalFile med);
    void deleteMed(int id);
    File generateFile();
    MedicalFile searchMed(int id);
    void addAllergie();
    void addNotes();
    List<Visit> getVistits(Patient pa);
    void archive();


}
