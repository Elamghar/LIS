package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Visit;

import java.util.List;

public interface PatientDao {
    void save(Patient patient);
    void delete(Patient patient);
    Patient searchById(String patientId);
    List<Patient> searchByFirstName(String firstName);
    List<Patient> searchByLastName(String lastName);
    List<Patient> searchByAge(int age);
    List<Patient> getAllPatients();
    List<Visit> getVisits(Patient patient);
}
