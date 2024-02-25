package com.nhnacademy.springboottask.exception;

public class DeleteMilestoneException extends RuntimeException {
    public DeleteMilestoneException() {
        super();
    }

    public DeleteMilestoneException(String info) {
        super(info);
    }
}
