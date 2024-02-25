package com.nhnacademy.springboottask.exception;

public class DeleteMemberException extends RuntimeException{
    public DeleteMemberException() {
        super();
    }

    public DeleteMemberException(String info) {
        super(info);
    }
}
