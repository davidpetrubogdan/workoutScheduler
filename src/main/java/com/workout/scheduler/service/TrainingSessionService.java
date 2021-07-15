package com.workout.scheduler.service;

import com.workout.scheduler.exceptions.TrainingNotFoundException;
import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.dto.Mapper;
import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.repository.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrainingSessionService {
    @Autowired
    private TrainingSessionRepository repository;

    public TrainingSessionDTO save(@Valid  TrainingSessionDTO trainingSessionDTO) {
        return Mapper.from(repository.save(Mapper.from(trainingSessionDTO)));
    }


    public List<TrainingSessionDTO> findAll() {
        return repository.findAll().stream().map(Mapper::from).collect(Collectors.toList());
    }

    public TrainingSessionDTO findById(Long id) {
        TrainingSession trainingSession = repository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
        return Mapper.from(trainingSession);
    }

    public TrainingSessionDTO update(TrainingSessionDTO newTrainingSessionDTO, Long id) {
        TrainingSession newTraining = Mapper.from(newTrainingSessionDTO);
        TrainingSessionDTO updatedTraining = Mapper.from(repository.findById(id) //
                .map(training -> {
                    training.setDay(newTraining.getDay());
                    training.setHours(newTraining.getHours());
                    training.setType(newTraining.getType());
                    return repository.save(training);
                }) //
                .orElseGet(() -> {
                    newTraining.setId(id);
                    return repository.save(newTraining);
                }));
        return updatedTraining;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }


}
