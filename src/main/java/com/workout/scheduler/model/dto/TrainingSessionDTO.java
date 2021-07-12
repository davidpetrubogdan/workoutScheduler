package com.workout.scheduler.model.dto;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSessionDTO {
    private Long id;
    private Integer hours;
    private String type;
    //private Date date;
    private String day;
}
