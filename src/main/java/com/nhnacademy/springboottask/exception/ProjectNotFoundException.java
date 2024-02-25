package com.nhnacademy.springboottask.exception;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(String info) {
        super(info);
    }
}
