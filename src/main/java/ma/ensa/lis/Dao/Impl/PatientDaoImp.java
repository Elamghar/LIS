package ma.ensa.lis.Dao.Impl;

import ma.ensa.lis.Dao.PatientDao;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.TestLab;
import ma.ensa.lis.utils.DbConnection;
import ma.ensa.lis.utils.useFullFunction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImp implements PatientDao {

    @Override
    public void save(Patient patient, List<TestLab> tests) {
        System.out.println("save patient");
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "INSERT INTO Patient (CIN, firstName, lastName, age, gender, email, address, phoneNumber) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, patient.getCIN());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getLastName());
            stmt.setInt(4, patient.getAge());
            stmt.setString(5, patient.getGender());
            stmt.setString(6, patient.getEmail());
            stmt.setString(7, patient.getAddress());
            stmt.setString(8,patient.getPhoneNumber());

            stmt.executeUpdate();

            // Associer les tests au patient
            String testQuery = "INSERT INTO Patient_test (CIN, testid,dateTEST,testName,selected) VALUES (?,?,? ,?,?)";
            PreparedStatement testStmt = conn.prepareStatement(testQuery);
            for (TestLab test : tests) {
                testStmt.setString(1, patient.getCIN());
                testStmt.setString(2, test.getId());
                testStmt.setTimestamp(3, java.sql.Timestamp.valueOf(LocalDateTime.now()));
                testStmt.setString(4, test.getName());
                testStmt.setBoolean(5,true);
                testStmt.executeUpdate();
            }
            System.out.println("Patient added successfully !!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String findemail(String CIN) throws SQLException {
        System.out.println("entered to findcin");
        DbConnection db=new DbConnection();
        Connection connection=db.getConn();
        String sql="SELECT * FROM patient WHERE email = ?";
        PreparedStatement stmt=connection.prepareStatement(sql);
        stmt.setString(1, CIN);
        ResultSet rs=stmt.executeQuery();
        if (rs.next()) {
            String email=rs.getString("email");
            System.out.println(email);
            return email;
        } else {
            useFullFunction.ShowAlert("user not found","there is no patient with this email");
            return null;
        }
    }
    @Override
    public void delete(Patient patient) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "DELETE FROM Patient WHERE CIN = ? and firstName= ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, patient.getCIN());
            stmt.setString(2, patient.getFirstName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient searchByCIN(String CIN) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "SELECT * FROM Patient WHERE CIN= ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1,  CIN);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Patient(
                        rs.getString("CIN"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("address")
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
                        rs.getString("CIN"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("address")
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
                        rs.getString("CIN"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("address")
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
                        rs.getString("CIN"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("address")
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
                        rs.getString("CIN"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("email"),
                        rs.getString("address")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patients;
    }
}

