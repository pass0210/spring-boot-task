package com.nhnacademy.springboottask.exception;

public class DeleteCommentException extends RuntimeException {
    public DeleteCommentException() {
        super();
    }

    public DeleteCommentException(String info) {
        super(info);
    }
}
