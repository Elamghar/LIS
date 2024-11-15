package ma.ensa.lis.models;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String patientId,nom,prenom,gender;
    List<Visit> visits;
    private int age;
    public Patient(String patientId,String nom,String prenom,int age,String gender){
        this.patientId=patientId;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.gender=gender;
    }
}
