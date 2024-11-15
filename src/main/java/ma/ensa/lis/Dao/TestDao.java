package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Test;
import java.util.List;

public interface TestDao {

    Test findById(String id);
    List<Test> findAll();
    void save(Test test);
    void update(Test test);
    void delete(String id);

}

