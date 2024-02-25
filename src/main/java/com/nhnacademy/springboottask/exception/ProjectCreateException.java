package com.nhnacademy.springboottask.exception;

public class ProjectCreateException extends RuntimeException{
    public ProjectCreateException() {
        super();
    }

    public ProjectCreateException(String info) {
        super(info);
    }
}
