package com.nhnacademy.springboottask.exception;

public class UpdateTaskException extends RuntimeException {
    public UpdateTaskException() {
        super();
    }

    public UpdateTaskException(String info) {
        super(info);
    }
}
