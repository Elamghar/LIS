package ma.ensa.lis.Exceptions;

public class IllnessException extends Exception{
    public String msg;


    public IllnessException(String msg){
        super(msg);
        this.msg=msg;

    }
}
