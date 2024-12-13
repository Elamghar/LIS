<<<<<<< HEAD
package ma.ensa.lis.Dao.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ensa.lis.Dao.MedicalFileDao;
import ma.ensa.lis.models.MedicalFile;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.utils.DbConnection;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalFileImp implements MedicalFileDao {


    @Override
    public void saveMed(MedicalFile med){
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String query="INSERT INTO medicalfile  VALUES(null,?,?,?,?)";//id feh null auto increment
        try{
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(med.getDateCrea().getTime()));
            stmt.setDate(2,(Date) med.getDateModif());
            String allergiesJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(med.getAllergies());//allergis kaynin ka json f database hya o notes
            String notesJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(med.getNotes());//kan7wlo list l json string
            stmt.setString(3,allergiesJson);
            stmt.setString(4,notesJson);
            int rs=stmt.executeUpdate();
            if(rs>0){
                System.out.print("data saved successifuly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteMed(int id) {//ila mab9ach kayji >1ans test if required bla mat3mer database
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String Query="DELETE FROM medicalfile WHERE id=?";
        try{
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setInt(1,id);
            int rs=stmt.executeUpdate();//rs hya rows affected
            if(rs>0){
                System.out.print("deleted successifuly");
            }else{
                System.out.print("not deleted successifuly");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public File generateFile() {
        return null;
    }

    @Override
    public MedicalFile searchMed(int id) {
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String Query="SELECT * FROM medicalfile WHERE id=? ";
        try{
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                System.out.print("found");
                String allergis=rs.getString("allergies");
                String notes=rs.getString("Notes");

                //List<Visit> visits=
                ObjectMapper objectMapper = new ObjectMapper();
                MedicalFile med=new MedicalFile();
                if(!(allergis ==null) && !(notes==null)) {
                    List<String> allergies = objectMapper.readValue(allergis, new TypeReference<List<String>>() {
                    });
                    List<String> note = objectMapper.readValue(notes,new TypeReference<List<String>>() {});
                    med.setAllergies(allergies);
                    med.setNotes(note);
                }



                med.setId(rs.getInt("id"));
                med.setDateCrea(rs.getDate("date_crea"));
                med.setDateModif(rs.getDate("date_modif"));

                return med;
            }else{
                System.out.print("not found");
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addAllergie() {

    }

    @Override
    public void addNotes() {

    }
    @Override
    public void archive() {

    }
}
=======
package ma.ensa.lis.Dao.Impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ma.ensa.lis.Dao.MedicalFileDao;
import ma.ensa.lis.models.MedicalFile;
import ma.ensa.lis.models.Patient;
import ma.ensa.lis.models.Visit;
import ma.ensa.lis.utils.DbConnection;

import javax.swing.plaf.nimbus.State;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MedicalFileImp implements MedicalFileDao {


    @Override
    public void saveMed(MedicalFile med){
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String query="INSERT INTO medicalfile  VALUES(null,?,?,?,?)";//id feh null auto increment
        try{
            PreparedStatement stmt=conn.prepareStatement(query);
            stmt.setDate(1, new java.sql.Date(med.getDateCrea().getTime()));
            stmt.setDate(2,(Date) med.getDateModif());
            String allergiesJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(med.getAllergies());//allergis kaynin ka json f database hya o notes
            String notesJson = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(med.getNotes());//kan7wlo list l json string
            stmt.setString(3,allergiesJson);
            stmt.setString(4,notesJson);
            int rs=stmt.executeUpdate();
            if(rs>0){
                System.out.print("data saved successifuly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteMed(int id) {//ila mab9ach kayji >1ans test if required bla mat3mer database
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String Query="DELETE FROM medicalfile WHERE id=?";
        try{
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setInt(1,id);
            int rs=stmt.executeUpdate();//rs hya rows affected
            if(rs>0){
                System.out.print("deleted successifuly");
            }else{
                System.out.print("not deleted successifuly");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public File generateFile() {
        return null;
    }

    @Override
    public MedicalFile searchMed(int id) {
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String Query="SELECT * FROM medicalfile WHERE id=? ";
        try{
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setInt(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                System.out.print("found");
                String allergis=rs.getString("allergies");
                String notes=rs.getString("Notes");

                //List<Visit> visits=
                ObjectMapper objectMapper = new ObjectMapper();
                MedicalFile med=new MedicalFile();
                if(!(allergis ==null) && !(notes==null)) {
                    List<String> allergies = objectMapper.readValue(allergis, new TypeReference<List<String>>() {
                    });
                    List<String> note = objectMapper.readValue(notes,new TypeReference<List<String>>() {});
                    med.setAllergies(allergies);
                    med.setNotes(note);
                }



                med.setId(rs.getInt("id"));
                med.setDateCrea(rs.getDate("date_crea"));
                med.setDateModif(rs.getDate("date_modif"));

                return med;
            }else{
                System.out.print("not found");
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        } catch (StreamReadException e) {
            throw new RuntimeException(e);
        } catch (DatabindException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void addAllergie() {

    }

    @Override
    public void addNotes() {

    }

    @Override
    public List<Visit> getVistits(Patient pa) {
        List<Visit> visits=new ArrayList<>();
        DbConnection db=new DbConnection();
        Connection conn=db.getConn();
        String Query="SELECT * FROM visit WHERE patientId=? ";//patient id colomn in visit table ..khassna jointure
        try{
            PreparedStatement stmt=conn.prepareStatement(Query);
            stmt.setString(1,String.valueOf(pa.getId()));
            ResultSet rs=stmt.executeQuery();
            while(rs.next()){
                Visit visit=new Visit(rs.getString("id"),rs.getDate("date"),rs.getString("diagnostic"));
                visits.add(visit);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return visits;
    }

    @Override
    public void archive() {

    }
}
>>>>>>> 19ddaf391c7eaf4cd98be386662e7d37b29a9875
