package com.nhnacademy.springboottask.exception;

public class CreateTagException extends RuntimeException{
    public CreateTagException() {
        super();
    }

    public CreateTagException(String info) {
        super(info);
    }
}
