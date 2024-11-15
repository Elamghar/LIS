package ma.ensa.lis.models;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private  int id;
    private String nom,prenom,gender;
    List<Visit> visits;
    private int age;
    public Patient(String patientId,String nom,String prenom,int age,String gender){
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
        this.age=age;
        this.gender=gender;
    }

    public Patient(int id, String namee, String prenom,String gender) {
         this.id=id;
         this.nom=namee;
         this.prenom=prenom;
         this.gender=gender;
    }
}
