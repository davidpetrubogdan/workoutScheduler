package com.workout.scheduler.model.dto;

import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.TrainingType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.DayOfWeek;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class MapperTest {

    @Test
    public void fromEntityTest() throws Exception{
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO(1L,2,"HIIT","SUNDAY");
        TrainingSession givenTrainingSession = new TrainingSession(1L, 2, TrainingType.HIIT,DayOfWeek.SUNDAY);
        TrainingSessionDTO resultDto = Mapper.from(givenTrainingSession);
        assertEquals(givenTrainingSessionDTO.getId(),resultDto.getId());
        assertEquals(givenTrainingSessionDTO.getHours(),resultDto.getHours());
        assertEquals(givenTrainingSessionDTO.getType(),resultDto.getType());
        assertEquals(givenTrainingSessionDTO.getDay(),resultDto.getDay());
    }
    @Test
    public void fromDtoTest() throws Exception{
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO(1L,2,"HIIT","SUNDAY");
        TrainingSession givenTrainingSession = new TrainingSession(1L, 2, TrainingType.HIIT,DayOfWeek.SUNDAY);
        TrainingSession result = Mapper.from(givenTrainingSessionDTO);
        assertEquals(givenTrainingSession.getId(),result.getId());
        assertEquals(givenTrainingSession.getHours(),result.getHours());
        assertEquals(givenTrainingSession.getType(),result.getType());
        assertEquals(givenTrainingSession.getDay(),result.getDay());
    }
}