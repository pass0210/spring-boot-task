package com.nhnacademy.springboottask.exception;

public class UpdateMilestoneException extends RuntimeException {
    public UpdateMilestoneException() {
        super();
    }

    public UpdateMilestoneException(String info) {
        super(info);
    }
}
