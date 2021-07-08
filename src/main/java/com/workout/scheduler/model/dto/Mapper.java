package com.workout.scheduler.model.dto;

import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.TrainingType;

import java.time.DayOfWeek;

public class Mapper {

    public static TrainingSessionDTO from(TrainingSession trainingSession){
        return TrainingSessionDTO.builder()
                .day(trainingSession.getDay().toString())
                .id(trainingSession.getId())
                .hours(trainingSession.getHours())
                .type(trainingSession.getType().toString())
                .build();
    }
    public static TrainingSession from(TrainingSessionDTO trainingSession){
        return TrainingSession.builder()
                .day(DayOfWeek.valueOf(trainingSession.getDay()))
                .id(trainingSession.getId())
                .hours(trainingSession.getHours())
                .type(TrainingType.valueOf(trainingSession.getType()))
                .build();
    }
}
