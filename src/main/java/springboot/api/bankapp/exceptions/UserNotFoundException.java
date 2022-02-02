package springboot.api.bankapp.exceptions;

import springboot.api.bankapp.data.models.User;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super();
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
