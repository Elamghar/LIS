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
            stmt.setString(2, v.getVisitDate());
            stmt.setString(2, v.getVisitDiagno());
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

    }

    @Override
    public Visit search(String id) {
        return null;
    }

    @Override
    public Visit search(Date dateVisit) {
        return null;
    }
}
