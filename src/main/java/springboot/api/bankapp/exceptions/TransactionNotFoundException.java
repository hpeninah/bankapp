package springboot.api.bankapp.exceptions;

public class TransactionNotFoundException extends Exception{
    public TransactionNotFoundException() {
        super();
    }

    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }

    public TransactionNotFoundException(Throwable cause) {
        super(cause);
    }
}
