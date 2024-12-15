package ma.ensa.lis.models;

import lombok.*;
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
    private List<TestLab> tests;

}

