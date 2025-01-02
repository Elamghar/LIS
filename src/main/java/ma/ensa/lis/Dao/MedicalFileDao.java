package ma.ensa.lis.Dao;

import ma.ensa.lis.models.MedicalFile;

import java.io.File;

public interface MedicalFileDao {

    void saveMed(MedicalFile med);
    void deleteMed(int id);
    File generateFile();
    MedicalFile searchMed(int id);
}
