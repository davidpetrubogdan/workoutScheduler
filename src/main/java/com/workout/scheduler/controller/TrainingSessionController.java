package com.workout.scheduler.controller;

import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.service.TrainingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class TrainingSessionController {
    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping("/trainings")
    public TrainingSessionDTO newTraining(@RequestBody TrainingSessionDTO newTraining) {
        return trainingSessionService.save(newTraining);
    }

    @GetMapping("/trainings")
    public List<TrainingSessionDTO> all() {
        return trainingSessionService.findAll();
    }
    @GetMapping("/trainings/{id}")
    public TrainingSessionDTO one(@PathVariable Long id) {
        return trainingSessionService.findById(id);
    }

    @PutMapping("/trainings/{id}")
    public TrainingSessionDTO replaceTraining(@RequestBody TrainingSessionDTO newTraining, @PathVariable Long id) {
        return trainingSessionService.update(newTraining,id);
    }

    @DeleteMapping("/trainings/{id}")
    public void deleteTraining(@PathVariable Long id) {
        trainingSessionService.deleteById(id);
    }
}
