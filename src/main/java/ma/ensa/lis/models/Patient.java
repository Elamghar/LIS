package ma.ensa.lis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@ToString(callSuper = true)
public class Patient  {
    private  String role;
    private  String phoneNumber;
    private String Id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String address;



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

    public Patient(String id, String firstName, String prenomm, String gender) {
        this.Id=id;
        this.firstName=firstName;
        this.lastName=prenomm;
        this.gender=gender;
    }

    public Patient(String id, String firstName, String prenomm, int age, String gender, String email, String address) {
    }



    public Patient(String patientId, String firstName, String lastName, int age, String gender, String email, String address, Object o) {

    }
}

