package ma.ensa.lis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Patient {

    private String patientId,nom,prenom;
    List<Visit> visits;

    public Patient(String patientId,String nom,String prenom){
        this.patientId=patientId;
        this.nom=nom;
        this.prenom=prenom;
    }
}
