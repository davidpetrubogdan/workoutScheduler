package com.workout.scheduler;

import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.TrainingType;
import com.workout.scheduler.repository.TrainingSessionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.DayOfWeek;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TrainingSessionRepository trainingSessionRepository) {

        return args -> {
    //        trainingSessionRepository.save(new TrainingSession(1, TrainingType.CARDIO, DayOfWeek.MONDAY));
     //       trainingSessionRepository.save(new TrainingSession(2, TrainingType.STRENGTH, DayOfWeek.TUESDAY));

            trainingSessionRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));
        };
    }
}