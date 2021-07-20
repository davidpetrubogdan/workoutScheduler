package com.workout.scheduler.service;

import com.workout.scheduler.exceptions.TrainingNotFoundException;
import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.TrainingType;
import com.workout.scheduler.model.dto.Mapper;
import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.repository.TrainingSessionRepository;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class TrainingSessionServiceTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    TrainingSessionRepository trainingSessionRepository;
    @InjectMocks
    TrainingSessionService trainingSessionService;

    @Test
    void createTrainingSuccessfully() {
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setHours(2);
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setType(TrainingType.HIIT);

        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);

            when(trainingSessionRepository.save(Mockito.any(TrainingSession.class))).thenAnswer(i -> i.getArguments()[0]);//thenReturn(new TrainingSession());
            TrainingSessionDTO created = trainingSessionService.save(givenTrainingSessionDTO);
            assertThat(created.getDay()).isSameAs(givenTrainingSessionDTO.getDay());
            assertThat(created.getType()).isSameAs(givenTrainingSessionDTO.getType());
            assertThat(created.getHours()).isSameAs(givenTrainingSessionDTO.getHours());

            verify(trainingSessionRepository).save(givenTrainingSession);

        }
    }
    @Test
    void findByIdTest() {
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setId(1L);
        givenTrainingSessionDTO.setHours(2);
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);
        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);

            when(trainingSessionRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(givenTrainingSession));
            TrainingSessionDTO created = trainingSessionService.findById(givenTrainingSessionDTO.getId());
            assertThat(created.getDay()).isSameAs(givenTrainingSessionDTO.getDay());
            assertThat(created.getType()).isSameAs(givenTrainingSessionDTO.getType());
            assertThat(created.getHours()).isSameAs(givenTrainingSessionDTO.getHours());

            verify(trainingSessionRepository).findById(givenTrainingSession.getId());
        }
    }

    @Test
    void findByIdExceptionTest(){

        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setId(1L);
        givenTrainingSessionDTO.setHours(2);
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);
        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);
            when(trainingSessionRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.ofNullable(null));
            assertThatThrownBy(()->trainingSessionService.findById(givenTrainingSessionDTO.getId())).isInstanceOf(TrainingNotFoundException.class).hasMessageContaining("Could not find training");
        }
    }

    @Test
    void updateEntityFoundTest() {
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setId(1L);
        givenTrainingSessionDTO.setHours(2);
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);

        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);

            when(trainingSessionRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.of(givenTrainingSession));
            when(trainingSessionRepository.save(Mockito.any(TrainingSession.class))).thenAnswer(i -> i.getArguments()[0]);

            TrainingSessionDTO created = trainingSessionService.update(givenTrainingSessionDTO,givenTrainingSessionDTO.getId());
            assertThat(created.getDay()).isSameAs(givenTrainingSessionDTO.getDay());
            assertThat(created.getType()).isSameAs(givenTrainingSessionDTO.getType());
            assertThat(created.getHours()).isSameAs(givenTrainingSessionDTO.getHours());

            verify(trainingSessionRepository).findById(givenTrainingSession.getId());
            verify(trainingSessionRepository).save(givenTrainingSession);
        }
    }
    @Test
    void updateEntityNotFoundTest() {
        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setId(1L);
        givenTrainingSessionDTO.setHours(2);
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);

        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);
            when(trainingSessionRepository.findById(Mockito.any(Long.class))).thenReturn(java.util.Optional.ofNullable(null));
            when(trainingSessionRepository.save(Mockito.any(TrainingSession.class))).thenAnswer(i -> i.getArguments()[0]);
            TrainingSessionDTO created = trainingSessionService.update(givenTrainingSessionDTO,givenTrainingSessionDTO.getId());
            assertThat(created.getDay()).isSameAs(givenTrainingSessionDTO.getDay());
            assertThat(created.getDay()).isSameAs(givenTrainingSessionDTO.getDay());
            assertThat(created.getType()).isSameAs(givenTrainingSessionDTO.getType());
            assertThat(created.getHours()).isSameAs(givenTrainingSessionDTO.getHours());
        }
    }
    @Test
    void findAllTest(){
        List<TrainingSessionDTO> trainingSessionDTOList = new ArrayList<>();

        TrainingSessionDTO givenTrainingSessionDTO = new TrainingSessionDTO();
        givenTrainingSessionDTO.setType("HIIT");
        givenTrainingSessionDTO.setDay("SUNDAY");
        givenTrainingSessionDTO.setId(1L);
        givenTrainingSessionDTO.setHours(2);
        trainingSessionDTOList.add(givenTrainingSessionDTO);

        List<TrainingSession> trainingSessionList = new ArrayList<>();

        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);

        trainingSessionList.add(givenTrainingSession);

        try (MockedStatic<Mapper> theMock = Mockito.mockStatic(Mapper.class)) {
            theMock.when(() -> Mapper.from(givenTrainingSession))
                    .thenReturn(givenTrainingSessionDTO);
            theMock.when(() -> Mapper.from(givenTrainingSessionDTO))
                    .thenReturn(givenTrainingSession);

            when(trainingSessionRepository.findAll()).thenReturn(trainingSessionList);
            List<TrainingSessionDTO> created = trainingSessionService.findAll();
            assertThat(created.size()).isSameAs(trainingSessionDTOList.size());
            verify(trainingSessionRepository).findAll();
        }

    }

    @Test
    void deleteTest(){
        TrainingSession givenTrainingSession = new TrainingSession();
        givenTrainingSession.setDay(DayOfWeek.SUNDAY);
        givenTrainingSession.setHours(2);
        givenTrainingSession.setId(1L);
        givenTrainingSession.setType(TrainingType.HIIT);
        trainingSessionService.deleteById(givenTrainingSession.getId());

        verify(trainingSessionRepository,times(1)).deleteById(1L);
    }

}
