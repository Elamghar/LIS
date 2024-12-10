package ma.ensa.lis.models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class Patient extends User {

    private List<Visit> listOfVisits;

    public Patient(String Id, String firstName, String lastName, int age, String gender, String email,
                   String password, String address, String role, String phoneNumber) {
        super(Id, firstName, lastName, age, gender, email, password, address, role, phoneNumber);
        this.listOfVisits = new ArrayList<>();
    }

    public void addVisit(Visit visit) {
        if (this.listOfVisits == null) {
            this.listOfVisits = new ArrayList<>();
        }
        this.listOfVisits.add(visit);
    }
}
