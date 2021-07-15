package com.workout.scheduler.controller;

import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.service.TrainingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class TrainingSessionController {
    @Autowired
    private TrainingSessionService trainingSessionService;

    @PostMapping("/trainings")
    TrainingSessionDTO newTraining(@RequestBody TrainingSessionDTO newTraining) {
        return trainingSessionService.save(newTraining);
    }

    @GetMapping("/trainings")
    List<TrainingSessionDTO> all() {
        return trainingSessionService.findAll();
    }
    @GetMapping("/trainings/{id}")
    TrainingSessionDTO one(@PathVariable Long id) {
        TrainingSessionDTO trainingSessionDTO = trainingSessionService.findById(id);
        return trainingSessionDTO;
    }

    @PutMapping("/trainings/{id}")
    TrainingSessionDTO replaceTraining(@RequestBody TrainingSessionDTO newTraining, @PathVariable Long id) {
        return trainingSessionService.update(newTraining,id);
    }

    @DeleteMapping("/trainings/{id}")
    void deleteTraining(@PathVariable Long id) {
        trainingSessionService.deleteById(id);
    }
}
