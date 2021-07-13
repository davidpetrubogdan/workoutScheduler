package com.workout.scheduler.service;

import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.dto.Mapper;
import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.repository.TrainingSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor=@__(@Autowired))
public class TrainingSessionService {
    @Autowired
    private TrainingSessionRepository repository;

    public TrainingSessionDTO save(TrainingSessionDTO trainingSessionDTO){
        return Mapper.from(repository.save(Mapper.from(trainingSessionDTO)));
    }
    public List<TrainingSessionDTO> findAll(){
        //repository.findAll().stream().forEach(System.out::println);
        return repository.findAll().stream().map(Mapper::from).collect(Collectors.toList());
    }
    public TrainingSessionDTO findById(Long id){
        /*Optional<TrainingSession> trainingSession = repository.findById(id);
        if(trainingSession.isPresent())
            return Optional.of(Mapper.from(trainingSession.get()));
        return Optional.ofNullable(null);*/
        TrainingSession trainingSession = repository.findById(id).orElseThrow(() -> new TrainingNotFoundException(id));
        return Mapper.from(trainingSession);
    }
    public TrainingSessionDTO update(TrainingSessionDTO newTrainingSessionDTO, Long id){
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

    public void deleteById(Long id){
        repository.deleteById(id);
    }


}
