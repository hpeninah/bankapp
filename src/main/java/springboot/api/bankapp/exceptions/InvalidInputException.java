package springboot.api.bankapp.exceptions;

public class InvalidInputException extends Exception{
    public InvalidInputException(){
        super();
    }

    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputException(String message){
        super(message);
    }

    public InvalidInputException(Throwable cause) {
        super(cause);
    }
}
