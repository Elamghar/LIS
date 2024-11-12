package ma.ensa.lis.models;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;

import java.util.Date;
public class Visit {
    private String visitId;
    private Date visitDate;
    //private List<Test> tests ;
    //ajout d'un staff
    //diagnostic et traitement
    //Patient p;


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

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }
    void addPatient(Patient p){
     if(p==null)throw new InvalidePatientInstance("Invalide patient Instance");
     this.p=p;
    }

    public  String toJson(){
        //implementation to facilate Xml manip
        return null;
    }
}
