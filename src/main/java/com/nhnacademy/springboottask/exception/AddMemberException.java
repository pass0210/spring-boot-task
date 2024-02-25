package com.nhnacademy.springboottask.exception;

public class AddMemberException extends RuntimeException{
    public AddMemberException() {
        super();
    }

    public AddMemberException(String info) {
        super(info);
    }
}
