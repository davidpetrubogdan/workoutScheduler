package com.workout.scheduler.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workout.scheduler.exceptions.TrainingNotFoundException;
import com.workout.scheduler.model.TrainingSession;
import com.workout.scheduler.model.dto.TrainingSessionDTO;
import com.workout.scheduler.service.TrainingSessionService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrainingSessionController.class)
public class TrainingSessionControllerTest {

    @MockBean
    TrainingSessionService trainingSessionService;

    @Autowired
    private LocalValidatorFactoryBean validator;

    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createTrainingTest() throws Exception{

        TrainingSessionDTO request = new TrainingSessionDTO();
        request.setHours(2);
        request.setDay("SUNDAY");
        request.setType("CARDIO");

        TrainingSessionDTO response = new TrainingSessionDTO();
        response.setType(request.getType());
        response.setHours(request.getHours());
        response.setDay(request.getDay());

        when(trainingSessionService.save(Mockito.any(TrainingSessionDTO.class))).thenReturn(response);

        mockMvc.perform(post("/trainings")
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.type").value(request.getType()))
                .andExpect(jsonPath("$.day").value(request.getDay()))
                .andExpect(jsonPath("$.hours").value(request.getHours()));

        verify(trainingSessionService).save(request);

    }
    @Test
    public void validationTest() throws Exception{

        TrainingSessionDTO request = new TrainingSessionDTO();
        request.setHours(-1);
        request.setDay("");
        request.setType("");


        Set<ConstraintViolation<TrainingSessionDTO>> typeConstraintViolationSet = validator.validateProperty(request,"type");
        assertEquals(1,typeConstraintViolationSet.size());
        assertEquals("must not be empty", typeConstraintViolationSet.iterator().next().getMessage());

        Set<ConstraintViolation<TrainingSessionDTO>> dayConstraintViolationSet = validator.validateProperty(request,"day");
        assertEquals(1,dayConstraintViolationSet.size());
        assertEquals("must not be empty", dayConstraintViolationSet.iterator().next().getMessage());

        Set<ConstraintViolation<TrainingSessionDTO>> hoursConstraintViolationSet = validator.validateProperty(request,"hours");
        assertEquals(1,hoursConstraintViolationSet.size());
        assertEquals("must be greater than or equal to 1", hoursConstraintViolationSet.iterator().next().getMessage());

    }
   @Test
   public void findTrainingByIdTest() throws Exception{

       TrainingSessionDTO request = new TrainingSessionDTO();
       request.setId(1L);
       request.setHours(2);
       request.setDay("SUNDAY");
       request.setType("CARDIO");

       TrainingSessionDTO response = new TrainingSessionDTO();
       response.setId(request.getId());
       response.setType(request.getType());
       response.setHours(request.getHours());
       response.setDay(request.getDay());

       when(trainingSessionService.findById(Mockito.any(Long.class))).thenReturn(response);

       mockMvc.perform(get("/trainings/" + request.getId())
              // .content(mapper.writeValueAsString(request))
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.type").value(request.getType()))
               .andExpect(jsonPath("$.day").value(request.getDay()))
               .andExpect(jsonPath("$.hours").value(request.getHours()));
       verify(trainingSessionService).findById(request.getId());

   }

    @Test
    public void findAllTrainingsTest() throws Exception{
        List<TrainingSessionDTO> response = new ArrayList<>();
        TrainingSessionDTO responseElement = new TrainingSessionDTO();

        response.add(responseElement);

        when(trainingSessionService.findAll()).thenReturn(response);

        mockMvc.perform(get("/trainings/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(response.size()));

        verify(trainingSessionService).findAll();

    }
    @Test
    public void updateTrainingTest() throws Exception{
        TrainingSessionDTO request = new TrainingSessionDTO();
        request.setId(1L);
        request.setHours(2);
        request.setDay("SUNDAY");
        request.setType("CARDIO");

        TrainingSessionDTO response = new TrainingSessionDTO();
        response.setId(request.getId());
        response.setType(request.getType());
        response.setHours(request.getHours());
        response.setDay(request.getDay());

        when(trainingSessionService.update(Mockito.any(TrainingSessionDTO.class), Mockito.any(Long.class))).thenReturn(response);

        mockMvc.perform(put("/trainings/" + request.getId())
                .content(mapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.day").value(response.getDay()))
                .andExpect(jsonPath("$.type").value(request.getType()))
                .andExpect(jsonPath("$.hours").value(request.getHours()));
        verify(trainingSessionService).update(request,request.getId());
    }

    @Test
    public void deleteTrainingTest() throws Exception{
        TrainingSessionDTO request = new TrainingSessionDTO();
        request.setId(1L);
        request.setHours(2);
        request.setDay("SUNDAY");
        request.setType("CARDIO");

        TrainingSessionDTO response = new TrainingSessionDTO();
        response.setId(request.getId());
        response.setType(request.getType());
        response.setHours(request.getHours());
        response.setDay(request.getDay());



        mockMvc.perform(delete("/trainings/" + request.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(trainingSessionService).deleteById(request.getId());

    }


}