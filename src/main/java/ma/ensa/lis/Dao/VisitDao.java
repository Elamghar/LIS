package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public interface  VisitDao {
    public void save(Visit v);
    public void delete(Visit v);
    public Visit findById(String id);
    public Visit findByDate(Date dateVisit);
    public Visit mapRowToVisit(ResultSet rs) throws SQLException;
    //public void search(Patient p);

}
