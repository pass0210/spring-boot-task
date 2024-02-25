package com.nhnacademy.springboottask.exception;

public class MilestoneNotFoundException extends RuntimeException {
    public MilestoneNotFoundException() {
        super();
    }

    public MilestoneNotFoundException(String info) {
        super(info);
    }
}
