package springboot.api.bankapp.exceptions;

public class MissingFieldException extends Exception{
    public MissingFieldException() {
        super();
    }

    public MissingFieldException(String message, Throwable cause){
        super(message, cause);
    }

    public MissingFieldException(String message){
        super(message);
    }

    public MissingFieldException(Throwable cause) {
        super(cause);
    }
}
