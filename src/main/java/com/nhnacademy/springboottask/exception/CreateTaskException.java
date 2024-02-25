package com.nhnacademy.springboottask.exception;

public class CreateTaskException extends RuntimeException {
    public CreateTaskException() {
        super();
    }

    public CreateTaskException(String info) {
        super(info);
    }
}
