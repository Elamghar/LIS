package ma.ensa.lis.Exceptions;

public class TestException extends RuntimeException {

    public String msg;

    public TestException(String m){
        super(m);
        msg=m;
    }
}
