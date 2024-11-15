package ma.ensa.lis.models;

import ma.ensa.lis.Exceptions.patientException;

import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Visit {

    private String visitId;
    private Date visitDate;
    private String diagnostic;
    //private List<Test> tests ;
    //ajout d'un staff
    Patient p;

    public Visit(String visitId,Date visitDate){
        this.visitId=visitId;
        this.visitDate=visitDate;
        //p=new Patient();
    }

    void addPatient(Patient p) throws patientException {
        if(p==null)throw new patientException("Instance Patient is null");
        this.p=p;
    }

    public  String toJson(){
        //implementation to facilate Xml manip
        return null;
    }
}
