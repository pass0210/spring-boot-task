package com.nhnacademy.springboottask.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException() {
        super();
    }

    public CommentNotFoundException(String info) {
        super(info);
    }
}
