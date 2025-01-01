package ma.ensa.lis.models;

import java.util.Date;

public class Patient_test {
//    CIN VARCHAR(50) NOT NULL,
//    testId INT NOT NULL,
//    dateTEST DATE NOT NULL,
//    testName VARCHAR(20) NOT NULL,
//    selected BOOLEAN DEFAULT FALSE,

    private int testId;
    private String CIN;
    private Date dateTest;
    private String testname;

    public Patient_test(String name, String cin, Date date) {
        this.testname=name;
        this.CIN=cin;
        this.dateTest=date;
    }
    public String  getTestname(){
        return this.testname;
    }
    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }
    public Date getDateTest() {
        return dateTest;
    }
}
