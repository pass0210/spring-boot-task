package com.nhnacademy.springboottask.exception;

public class CreateMilestoneException extends RuntimeException {
    public CreateMilestoneException() {
        super();
    }

    public CreateMilestoneException(String info) {
        super(info);
    }
}
