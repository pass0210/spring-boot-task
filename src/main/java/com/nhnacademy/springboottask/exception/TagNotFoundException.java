package com.nhnacademy.springboottask.exception;

public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super();
    }

    public TagNotFoundException(String info) {
        super(info);
    }
}
