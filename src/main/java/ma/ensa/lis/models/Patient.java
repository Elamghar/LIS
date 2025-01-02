package ma.ensa.lis.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString()

public class Patient  {
    private String CIN;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String address;
    private String email;
    private String phoneNumber;
    private List<TestLab> tests = new ArrayList<>();

    public Patient(String CIN, String firstName, String lastName, int age, String gender, String email, String address) {
        this.CIN = CIN;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
    }

    public Patient(String cin, String firstName, String lastName, int age, String gender, String email, String address, String phoneNumber) {
        this.CIN=cin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Patient(String id, String firstName, String prenom, String gender) {
        this.CIN=id;
        this.firstName = firstName;
        this.lastName = prenom;
        this.gender = gender;
    }

    public Patient(String email) {
        String[] tokens = email.split(" ");
    }

    public Patient(String CIN,int t) {
        this.CIN=CIN;

    }
}

