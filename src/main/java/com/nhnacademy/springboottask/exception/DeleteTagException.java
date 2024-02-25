package com.nhnacademy.springboottask.exception;

public class DeleteTagException extends RuntimeException {
    public DeleteTagException() {
        super();
    }

    public DeleteTagException(String info) {
        super(info);
    }
}
