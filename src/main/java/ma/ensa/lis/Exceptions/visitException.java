package ma.ensa.lis.Exceptions;

public class  visitException extends  Exception {
    public String msg;

    public visitException(String m){
        super(m);
        msg=m;
    }
}
