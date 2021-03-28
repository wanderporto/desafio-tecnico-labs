package com.luizalabs.tracking.service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

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

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        schedule = new Schedule();
		schedule.setSendAt(LocalDateTime.now());
		schedule.setMessage("send mensagge test");
		schedule.setRecipient("wander wang");

        this.mockScheduleRepository = Mockito.spy(ScheduleRepository.class);
        this.mockScheduleService = new ScheduleServiceImpl(this.mockScheduleRepository);
    }


    @Test
    public void should_create_schedule(){
        RequestScheduleDto scheduleDto = new RequestScheduleDto();
		scheduleDto.setSendAt(LocalDateTime.now());
		scheduleDto.setMessage("send mensagge test");
		scheduleDto.setRecipient("wander wang");

        Mockito.when(mockScheduleRepository.save(this.schedule)).thenReturn(this.schedule);

        this.mockScheduleService.create(scheduleDto);

		verify(mockScheduleRepository, times(1)).save(any(Schedule.class));
    }


    @Test
    public void should_deleted_schedule(){
        doNothing().when(mockScheduleRepository).deleteById(1l);

		verify(mockScheduleRepository, times(1)).save(any(Schedule.class));
    }
    
}