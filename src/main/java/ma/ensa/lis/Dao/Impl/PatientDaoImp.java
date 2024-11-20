package ma.ensa.lis.Dao.Impl;

import ma.ensa.lis.Dao.PatientDao;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Visit;
import ma.ensa.lis.utils.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImp implements PatientDao {

    @Override
    public void save(Patient patient) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "INSERT INTO Patient (patientId, firstName, lastName, age, gender, email, password, address, role, phoneNumber) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patient.getId());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getLastName());
            stmt.setInt(4, patient.getAge());
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getPassword());
            stmt.setString(8, patient.getAddress());
            stmt.setString(9, patient.getRole());
            stmt.setString(10, patient.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Patient patient) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "DELETE FROM Patient WHERE patientId = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patient.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient searchById(String patientId) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "SELECT * FROM Patient WHERE patientId = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patientId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getString("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("phoneNumber")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Patient> searchByFirstName(String firstName) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient WHERE firstName LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + firstName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> searchByLastName(String lastName) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient WHERE lastName LIKE ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, "%" + lastName + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> searchByAge(int age) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient WHERE age = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, age);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Patient> getAllPatients() {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patient";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                patients.add(new Patient(
                        rs.getString("patientId"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("role"),
                        rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }

    @Override
    public List<Visit> getVisits(Patient patient) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        List<Visit> visits = new ArrayList<>();
        String query = "SELECT * FROM Visit WHERE patientId = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patient.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                visits.add(new Visit(
                        rs.getString("visitId"),
                        rs.getDate("visitDate"),
                        rs.getString("description")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return visits;
    }
}