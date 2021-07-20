package com.workout.scheduler.exceptions;

public class TrainingNotFoundException extends RuntimeException {

    public TrainingNotFoundException(Long id) {
        super("Could not find training " + id);


    }

}
