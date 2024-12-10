package ma.ensa.lis.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString(callSuper = true)
public class Patient  {
    private String Id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String address;
    private String role;
    private String phoneNumber;

    private List<Visit> listOfVisits;

    public Patient(String patientId, String firstName, String lastName, int age, String gender, String email, String address, String role, String phoneNumber) {
        this.Id = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.role = role;
        this.phoneNumber = phoneNumber;
    }




    public void addVisit(Visit visit) {
        if (this.listOfVisits == null) {
            this.listOfVisits = new ArrayList<>();
        }
        this.listOfVisits.add(visit);
    }
}

