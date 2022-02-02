package springboot.api.bankapp.exceptions;

public class InvalidLoginException extends Exception{
    public InvalidLoginException() {
        super();
    }

    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLoginException(String message){
        super(message);
    }

    public InvalidLoginException(Throwable cause){
        super(cause);
    }
}
