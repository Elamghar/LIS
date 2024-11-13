package ma.ensa.lis.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ma.ensa.lis.Exceptions.patientException;

import java.util.Date;

@Data
public class Visit {
    @Setter
    @Getter
    private String visitId;
    @Setter
    @Getter
    private Date visitDate;
    private String diagnostic;
    //private List<Test> tests ;
    //ajout d'un staff
    Patient p;
    public Visit(){}
    public Visit(String visitId,Date visitDate){
        this.visitId=visitId;
        this.visitDate=visitDate;
        p=new Patient();
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
