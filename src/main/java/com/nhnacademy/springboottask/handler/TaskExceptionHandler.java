package com.nhnacademy.springboottask.handler;

import com.nhnacademy.springboottask.exception.CreateTaskException;
import com.nhnacademy.springboottask.exception.DeleteTaskException;
import com.nhnacademy.springboottask.exception.TaskNotFoundException;
import com.nhnacademy.springboottask.exception.UpdateTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TaskExceptionHandler {
    @ExceptionHandler(value = {CreateTaskException.class})
    public ResponseEntity<Void> taskBadRequestExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(value = {UpdateTaskException.class, DeleteTaskException.class, TaskNotFoundException.class})
    public ResponseEntity<Void> taskNotFoundExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
