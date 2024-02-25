package com.nhnacademy.springboottask.handler;

import com.nhnacademy.springboottask.exception.CreateMilestoneException;
import com.nhnacademy.springboottask.exception.DeleteMilestoneException;
import com.nhnacademy.springboottask.exception.MilestoneNotFoundException;
import com.nhnacademy.springboottask.exception.UpdateMilestoneException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MilestoneExceptionHandler {
    @ExceptionHandler(value = {CreateMilestoneException.class})
    public ResponseEntity<Void> milestoneBadRequestExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(value = {UpdateMilestoneException.class, DeleteMilestoneException.class, MilestoneNotFoundException.class})
    public ResponseEntity<Void> milestoneNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
