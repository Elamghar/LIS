package ma.ensa.lis.models;

import ma.ensa.lis.Exceptions.patientException;

import java.util.Date;
public class Visit {
    private String visitId;
    private Date visitDate;

    public String getDiagnostic() {
        return diagnostic;
    }

    //private List<Test> tests ;
    //ajout d'un staff
    //diagnostic et traitement
    public String diagnostic;
    Patient p;


    public Visit(){
    }
    public Visit(String visitId,Date visitDate){
        this.visitId=visitId;
        this.visitDate=visitDate;
        p=new Patient();
    }
    public String getVisitId() {
        return visitId;
    }

    public java.sql.Date getVisitDate() {
        return (java.sql.Date) visitDate;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    void addPatient(Patient p) throws patientException {
     if(p==null)throw new patientException();//"Invalide patient Instance"
     this.p=p;
    }

    public  String toJson(){
        //implementation to facilate Xml manip
        return null;
    }
}
