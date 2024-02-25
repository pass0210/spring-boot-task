package com.nhnacademy.springboottask.exception;

public class UpdateTagException extends RuntimeException{
    public UpdateTagException() {
        super();
    }

    public UpdateTagException(String info) {
        super(info);
    }
}
