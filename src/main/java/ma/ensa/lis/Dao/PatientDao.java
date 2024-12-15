package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;


import java.util.List;

public interface PatientDao {
    void save(Patient patient, List<TestLab> tests);
    void delete(Patient patient);
    Patient searchById(String Id);
    List<Patient> searchByFirstName(String firstName);
    List<Patient> searchByLastName(String lastName);
    List<Patient> searchByAge(int age);
    List<Patient> getAllPatients();

}
