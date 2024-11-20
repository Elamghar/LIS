package ma.ensa.lis.models;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String Id;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String email;
    private String password;
    private String address;
    private String role;
    private String phoneNumber;
}
