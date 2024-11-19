package ma.ensa.lis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Patient extends User {
    private String patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private List<Visit> listOfVisits;
    private String address;
    private String contactNumber;
}
