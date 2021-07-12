package com.workout.scheduler.service;

public class TrainingNotFoundException extends RuntimeException {

    public TrainingNotFoundException(Long id) {
        super("Could not find training " + id);


    }

}
