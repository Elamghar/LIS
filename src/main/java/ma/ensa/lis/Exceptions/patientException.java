package ma.ensa.lis.Exceptions;

public class patientException extends Exception {
    public String msg;


    public patientException(String msg){
        super(msg);
        this.msg=msg;

    }
}
