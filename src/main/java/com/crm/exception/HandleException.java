package com.crm.exception;

import com.crm.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

//class behaves  like catch block
@ControllerAdvice
public class HandleException extends Exception {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleEmployeeNotFoundException(
            Exception e,
            WebRequest request){
        ErrorDetails errorDetails=new ErrorDetails(
                new Date(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}