package ma.ensa.lis.models;

import lombok.*;

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

    public Patient(String patientId, String firstName, String lastName, int age, String gender, String email, String address, Object o) {
    }
}
