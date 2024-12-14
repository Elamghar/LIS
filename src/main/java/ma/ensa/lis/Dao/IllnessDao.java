package ma.ensa.lis.Dao;

import java.util.List;

public interface IllnessDao {
    void addIllness(Illness sickness);
    Illness getIllnessById(String id);
    List<Illness> getAllIllness();
    void updateIllness(Illness illness);
    void deleteIllness(String id);
}

