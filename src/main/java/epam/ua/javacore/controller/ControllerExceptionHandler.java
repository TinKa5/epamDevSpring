package epam.ua.javacore.controller;

import epam.ua.javacore.annotation.TimedPostProcessor;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger log = Logger.getLogger(ControllerExceptionHandler.class);

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(Exception.class)
    public void generalExceptionHandler(Exception e){
        log.error(e.getMessage());
    }



}
