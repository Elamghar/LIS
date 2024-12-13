package ma.ensa.lis.models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalFile {
    private int id;
    private Date dateCrea;
    private Date dateModif;
    Patient patient;
    Set<Visit> visit;
    List<String> Allergies;
    List<String> Notes;


}
>>>>>>> 19ddaf391c7eaf4cd98be386662e7d37b29a9875
