package ma.ensa.lis.Dao.Impl;

import ma.ensa.lis.Dao.VisitDao;
import ma.ensa.lis.models.Visit;
import ma.ensa.lis.utils.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class VisitDaoImp implements VisitDao {
    @Override
    public void save(Visit v) {
        DbConnection db = new DbConnection();
        Connection conn = db.getConn();
        String query = "INSERT INTO Visit (visitId,visitDate,diagnostic) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, v.getVisitId());
            stmt.setDate(2, (java.sql.Date) v.getVisitDate());
            stmt.setString(2, v.getDiagnostic());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Login Successful if match is found
                boolean success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //rs.close();

        }
    }

    @Override
    public void delete(Visit v) {
        String query =" DELETE From Visit WHERE visitId = ? ";
        DbConnection db=new DbConnection();
        try {
        Connection conn=db.getConn();
        PreparedStatement stmt=conn.prepareStatement(query);
        stmt.setString(1,v.getVisitId());
        stmt.executeUpdate();
        }catch (SQLException e) {
             e.printStackTrace();
        }
    }

    @Override
    public Visit findById(String visitId) {
            String query = "SELECT * FROM Visit WHERE id = ?";
            try{
                DbConnection db=new DbConnection();
                Connection conn=db.getConn();
                PreparedStatement stmt=conn.prepareStatement(query);
                stmt.setString(1,visitId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return mapRowToVisit(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

    @Override
    public Visit findByDate(Date dateVisit) {
        return null;
    }
    @Override
    public Visit mapRowToVisit(ResultSet rs) throws SQLException {
        Visit visit = new Visit();
        visit.setVisitId(rs.getString("visitId"));
        visit.setVisitDate(rs.getDate("visitDate"));
        visit.setDiagnostic(rs.getString("diagnostic"));
        return visit;
    }
}

