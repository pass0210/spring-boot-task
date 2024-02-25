package com.nhnacademy.springboottask.exception;

public class CreateCommentException extends RuntimeException{
    public CreateCommentException() {
        super();
    }

    public CreateCommentException(String info) {
        super(info);
    }
}
