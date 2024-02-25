package com.nhnacademy.springboottask.handler;

import com.nhnacademy.springboottask.exception.CreateTagException;
import com.nhnacademy.springboottask.exception.DeleteTagException;
import com.nhnacademy.springboottask.exception.TagNotFoundException;
import com.nhnacademy.springboottask.exception.UpdateTagException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TagExceptionHandler {
    @ExceptionHandler(value = {CreateTagException.class})
    public ResponseEntity<Void> tagBadRequestExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(value = {UpdateTagException.class, DeleteTagException.class, TagNotFoundException.class})
    public ResponseEntity<Void> tagNotFoundExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
