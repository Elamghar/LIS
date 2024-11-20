package ma.ensa.lis.Dao;

import ma.ensa.lis.models.Illness;

import java.util.List;

public interface IllnessDAO {
    void addIllness(Illness sickness);
    Illness getIllnessById(String id);
    List<Illness> getAllIllness();
    void updateIllness(Illness illness);
    void deleteIllness(String id);
}

