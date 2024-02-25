package com.nhnacademy.springboottask.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException() {
        super();
    }

    public TaskNotFoundException(String info) {
        super(info);
    }
}
