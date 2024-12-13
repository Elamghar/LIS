<<<<<<< HEAD
package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Patient;

import java.util.List;

public interface PatientDao {
    void save(Patient patient);
    void delete(Patient patient);
    Patient searchById(String Id);
    List<Patient> searchByFirstName(String firstName);
    List<Patient> searchByLastName(String lastName);
    List<Patient> searchByAge(int age);
    List<Patient> getAllPatients();
}
=======
package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Visit;

import java.util.List;

public interface PatientDao {
    void save(Patient patient);
    void delete(Patient patient);
    Patient searchById(String Id);
    List<Patient> searchByFirstName(String firstName);
    List<Patient> searchByLastName(String lastName);
    List<Patient> searchByAge(int age);
    List<Patient> getAllPatients();
    List<Visit> getVisits(Patient patient);
}
>>>>>>> 19ddaf391c7eaf4cd98be386662e7d37b29a9875
