package com.netsmartz.mis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionDetails getInstance(int code, String message, Exception e){
        return new ExceptionDetails(code,new Date(), message, e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UsernameNotFoundException exception){
        return new ResponseEntity<>(getInstance(1001,exception.getLocalizedMessage(), exception), HttpStatus.NO_CONTENT);
    }
    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<?> handleDisabledException(DisabledException exception){
        return new ResponseEntity<>(getInstance(1002, exception.getMessage(), exception), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handleBadCredentialsException(BadCredentialsException exception){
        return new ResponseEntity<>(getInstance(1003, exception.getLocalizedMessage(), exception), HttpStatus.NOT_ACCEPTABLE);
    }

    //Internal server Error
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException exception){
        return new ResponseEntity<>(getInstance(520,ExceptionConstant.INTERNAL_SERVER_ERROR, exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<?> handleArrayIndexOutOfBound(ArrayIndexOutOfBoundsException exception){
        return new ResponseEntity<>(getInstance(521, ExceptionConstant.INTERNAL_SERVER_ERROR, exception), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //handle un-excepted exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        return new ResponseEntity<>(getInstance(500, ExceptionConstant.INTERNAL_SERVER_ERROR, e), HttpStatus.INTERNAL_SERVER_ERROR );
    }

}
