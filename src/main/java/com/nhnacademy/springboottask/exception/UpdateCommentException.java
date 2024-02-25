package com.nhnacademy.springboottask.exception;

public class UpdateCommentException extends RuntimeException{
    public UpdateCommentException() {
        super();
    }

    public UpdateCommentException(String info) {
        super(info);
    }
}
