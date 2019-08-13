package com.stackroute.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
//The@ControllerAdvice annotation allows us to consolidate our multiple, scattered @ExceptionHandlers
// from before into a single, global error handling component.
public class GlobalException extends ResponseEntityExceptionHandler {
     @ExceptionHandler({TrackAlreadyExistsException.class})
    public ResponseEntity<String> handleTrackAlreadyExistsException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({TrackNotFoundException.class})
    public ResponseEntity<String> handleTrackNotFoundExceptions(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    }
}

