package com.luizalabs.tracking.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;
import java.util.Optional;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.repository.ScheduleRepository;
import com.luizalabs.tracking.service.ScheduleService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleServiceImplTest {

    private ScheduleService mockScheduleService;
    private ScheduleRepository mockScheduleRepository;
    
    private Schedule schedule;
    private RequestScheduleDto scheduleDto;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        
        this.schedule = new Schedule();
		schedule.setSendAt(LocalDateTime.now());
		schedule.setMessage("send mensagge test");
		schedule.setRecipient("wander wang");


        this.scheduleDto = new RequestScheduleDto();
		scheduleDto.setSendAt(LocalDateTime.now());
		scheduleDto.setMessage("send mensagge test");
		scheduleDto.setRecipient("wander wang");

        this.mockScheduleRepository = Mockito.spy(ScheduleRepository.class);
        this.mockScheduleService = new ScheduleServiceImpl(this.mockScheduleRepository);
    }

    @Test
    public void should_create_schedule(){
        Mockito.when(mockScheduleRepository.save(this.schedule)).thenReturn(this.schedule);
        this.mockScheduleService.create(scheduleDto);
		verify(mockScheduleRepository, times(1)).save(any(Schedule.class));
    }

    @Test
    public void should_deleted_schedule(){
        this.mockScheduleService.delete(1L);
		verify(mockScheduleRepository, times(1)).deleteById(1L);
    }

    @Test
    public void should_return_schedule_by_id(){
        Mockito.when(mockScheduleRepository.findById(1L)).thenReturn(Optional.of(this.schedule));
        this.mockScheduleService.getScheduleById(1L);
		verify(mockScheduleRepository, times(1)).findById(1L);
    }  
}