package ma.ensa.lis.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String userId;
    private String userName;
    private String password;
    private String role;
    private String email;
    private String phoneNumber;
}
