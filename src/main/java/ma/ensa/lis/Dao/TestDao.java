package ma.ensa.lis.Dao;

import ma.ensa.lis.models.TestLab;
import java.util.List;

public interface TestDao {

    TestLab findById(String id);
    List<TestLab> findAll();
    void save(TestLab test);
    void update(TestLab test);
    void delete(String id);

}

