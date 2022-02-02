package springboot.api.bankapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorResponse> method1(AccountNotFoundException accountNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = accountNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> method2(CustomerNotFoundException customerNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = customerNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> method3(RoleNotFoundException roleNotFoundException){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = roleNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> method4(TransactionNotFoundException transactionNotFoundException){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = transactionNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> method5(UserNotFoundException userNotFoundException) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = userNotFoundException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity<ErrorResponse> method6(InvalidLoginException invalidLoginException){
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = invalidLoginException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorResponse> method7(InvalidInputException invalidInputException) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = invalidInputException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingFieldException.class)
    public ResponseEntity<ErrorResponse> method8(MissingFieldException missingFieldException){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = missingFieldException.getMessage();
        ErrorResponse errorMessage = new ErrorResponse(status, message);
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
