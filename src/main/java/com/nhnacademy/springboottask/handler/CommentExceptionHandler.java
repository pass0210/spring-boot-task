package com.nhnacademy.springboottask.handler;

import com.nhnacademy.springboottask.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommentExceptionHandler {
    @ExceptionHandler(value = {CreateCommentException.class, GetCommentByTaskException.class})
    public ResponseEntity<Void> CommentBadRequestExceptionHandler() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(value = {UpdateCommentException.class, DeleteCommentException.class, CommentNotFoundException.class})
    public ResponseEntity<Void> CommentNotFoundException() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .build();
    }
}
