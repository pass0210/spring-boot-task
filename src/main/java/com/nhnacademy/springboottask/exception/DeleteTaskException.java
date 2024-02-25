package com.nhnacademy.springboottask.exception;

public class DeleteTaskException extends RuntimeException {
    public DeleteTaskException() {
        super();
    }

    public DeleteTaskException(String info) {
        super(info);
    }
}
