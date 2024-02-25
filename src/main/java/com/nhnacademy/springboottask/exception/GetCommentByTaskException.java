package com.nhnacademy.springboottask.exception;

public class GetCommentByTaskException extends RuntimeException {
    public GetCommentByTaskException() {
        super();
    }

    public GetCommentByTaskException(String info) {
        super(info);
    }
}
