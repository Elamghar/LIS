package ma.ensa.lis.controllers;

import ma.ensa.lis.Dao.IllnessDao;
import ma.ensa.lis.Dao.Impl.IllnessDaoImp;
import ma.ensa.lis.models.Illness;

import java.util.List;

public class IllnessController {
    private final IllnessDao illnessDao;

    public IllnessController(IllnessDao illnessDAO) {
        this.illnessDao = new IllnessDaoImp();
    }

    // Ajouter une nouvelle maladie
    public void addIllness(String illnessID, String name, String description) {
        if (illnessID == null || name == null || description == null) {
            throw new IllegalArgumentException("Les champs illnessID, name et description sont obligatoires.");
        }
        Illness illnness = new Illness(illnessID, name, description);
        illnessDao.addIllness(illnness);
    }

    // Récupérer une maladie par ID
    public Illness getIllnessById(String illnessID) {
        if (illnessID == null || illnessID.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la maladie ne peut pas être vide.");
        }
        return illnessDao.getIllnessById(illnessID);
    }

    //Recuperer tous les maladies
    public List<Illness> getAllIllnesses(){
        return illnessDao.getAllIllness();
    }

    // Mettre à jour une maladie
    public void updateIllness(String illnessID, String name, String description) {
        if (illnessID == null || name == null || description == null) {
            throw new IllegalArgumentException("Les champs sicknessID, name et description sont obligatoires.");
        }
        Illness illness = new Illness(illnessID, name, description);
        illnessDao.updateIllness(illness);
    }

    // Supprimer une maladie par ID
    public void deleteIllness(String illnessID) {
        if (illnessID == null || illnessID.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la maladie ne peut pas être vide.");
        }
        illnessDao.deleteIllness(illnessID);
    }

}
