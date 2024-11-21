package ma.ensa.lis.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.ensa.lis.Exceptions.TestException;
import ma.ensa.lis.Exceptions.patientException;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Visit {
    private String visitId;
    private Date visitDate;
    private String diagnostic;
    private List<TestLab> tests ;
    Patient p;



    public Visit(String visitId,Date visitDate){
        this.visitId=visitId;
        this.visitDate=visitDate;
        //p=new Patient();
    }
    public Visit(String visitId,Date visitDate,String diagnostic){
        this.visitId=visitId;
        this.visitDate=visitDate;
        this.diagnostic=diagnostic;
        //p=new Patient();
    }
    void addTest(TestLab test) throws  TestException {
        if(test==null)throw new TestException("Instance null in Visit.addTest");
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
