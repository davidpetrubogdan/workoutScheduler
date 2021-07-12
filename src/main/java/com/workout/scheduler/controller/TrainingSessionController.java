package com.workout.scheduler.controller;

import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.service.TrainingNotFoundException;
import com.workout.scheduler.service.TrainingSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
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
        TrainingSessionDTO trainingSessionDTO = trainingSessionService.findById(id) //
                .orElseThrow(() -> new TrainingNotFoundException(id));
        return trainingSessionDTO;
    }

    @PutMapping("/trainings/{id}")
    TrainingSessionDTO replaceTraining(@RequestBody TrainingSessionDTO newTraining, @PathVariable Long id) {
        TrainingSessionDTO updatedTraining = trainingSessionService.findById(id) //
                .map(training -> {
                    training.setDay(newTraining.getDay());
                    training.setHours(newTraining.getHours());
                    training.setType(newTraining.getType());
                    return trainingSessionService.save(training);
                }) //
                .orElseGet(() -> {
                    newTraining.setId(id);
                    return trainingSessionService.save(newTraining);
                });
        return updatedTraining;
    }

    @DeleteMapping("/trainings/{id}")
    void deleteTraining(@PathVariable Long id) {
        trainingSessionService.deleteById(id);

     //   return ResponseEntity.noContent().build();
    }
}
