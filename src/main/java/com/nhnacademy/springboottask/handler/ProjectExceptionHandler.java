package com.nhnacademy.springboottask.handler;

import com.nhnacademy.springboottask.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(value = {ProjectCreateException.class, AddMemberException.class, DeleteMemberException.class})
    public ResponseEntity<Void> projectBadRequestExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(value = {ProjectUpdateException.class, ProjectNotFoundException.class})
    public ResponseEntity<Void> projectNotFoundExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
