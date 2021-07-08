package com.workout.scheduler.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor

@AllArgsConstructor
@Entity
public class TrainingSession {
    private @Id @GeneratedValue Long id;
    private Integer hours;
    private TrainingType type;
    //private Date date;
    private DayOfWeek day;

    public TrainingSession(Integer hours, TrainingType type, DayOfWeek day){
        this.hours=hours;
        this.type=type;
        this.day=day;
    }

}
