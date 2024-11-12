package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Visit;

import java.util.Date;

public interface VisitDao {
    public void save(Visit v);
    public void delete(Visit v);
    public Visit search(String id);
    public Visit search(Date dateVisit);
    //public void search(Patient p);

}
