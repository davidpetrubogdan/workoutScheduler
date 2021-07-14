package com.workout.scheduler.model.dto;

import lombok.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Builder
@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingSessionDTO {
    private Long id;
    @Min(1)
    private Integer hours;
    @NotEmpty
    private String type;
    //private Date date;
    @NotEmpty
    private String day;
}
