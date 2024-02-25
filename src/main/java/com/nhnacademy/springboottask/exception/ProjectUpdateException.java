package com.nhnacademy.springboottask.exception;

public class ProjectUpdateException extends RuntimeException{
    public ProjectUpdateException() {
        super();
    }

    public ProjectUpdateException(String info) {
        super(info);
    }
}
