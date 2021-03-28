package com.luizalabs.tracking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.Optional;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.execption.ScheduleNotFound;
import com.luizalabs.tracking.repository.ScheduleRepository;
import com.luizalabs.tracking.service.impl.ScheduleServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc
public class ScheduleControllerTest {
    
	private String requestSchedule;
	private Schedule schedule;
	private RequestScheduleDto scheduleDto;

	@Autowired 
	private MockMvc mockMvc;

	@MockBean
	private ScheduleRepository mockScheduleRepository;

	@MockBean
	private ScheduleServiceImpl mockScheduleService;

	@Autowired
    private ScheduleController mockScheduleController;

	@BeforeEach
	public void setup(){
		this.mockMvc = MockMvcBuilders
			.standaloneSetup(mockScheduleController)
			.build();

		this.requestSchedule = "{\"recipient\":\"wander\",\"message\": \"test menssage schedule\",\"sendAt\":\"2020-11-23T23:59:59\"}";
				
		MockitoAnnotations.initMocks(this);
		
		this.schedule = new Schedule();
		schedule.setSendAt(LocalDateTime.now());
		schedule.setMessage("send message test");
		schedule.setRecipient("wander wang");

		this.scheduleDto = new RequestScheduleDto();
		scheduleDto.setSendAt(LocalDateTime.now());
		scheduleDto.setMessage("send message test");
		scheduleDto.setRecipient("wander wang");

	}

	@Test
	@Order(1)
	public void should_create_schedule() throws Exception {
		
		when(mockScheduleService.create(Mockito.any(RequestScheduleDto.class))).thenReturn(this.schedule);
	    
		this.mockMvc
        .perform(post("/v1/schedule/")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isCreated());
	}

	@Test
	public void case_recipient_isEmpty_then_should_statusCode_400() throws Exception {
		
		when(mockScheduleService.create(Mockito.any(RequestScheduleDto.class))).thenReturn(this.schedule);
	    
		this.requestSchedule = "{\"message\": \"testando\",\"sendAt\":\"2020-11-23T23:59:59\"}";
		this.mockMvc
        .perform(post("/v1/schedule/")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest());
	}

	@Test
	public void case_message_isEmpty_then_should_statusCode_400() throws Exception {
		
		when(mockScheduleService.create(Mockito.any(RequestScheduleDto.class))).thenReturn(this.schedule);
	    
		this.requestSchedule = "{\"recipient\":\"wander\",\"sendAt\":\"2020-11-23T23:59:59\"}";
		this.mockMvc
        .perform(post("/v1/schedule/")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest());
		
	}

	@Test
	public void case_sendAt_isEmpty_then_should_statusCode_400() throws Exception {
	    this.requestSchedule = "{\"recipient\":\"wander\",\"message\": \"testando\"}";
		this.mockMvc
        .perform(post("/v1/schedule/")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isBadRequest());
	}

	@Test
	public void should_deleted_schedule() throws Exception {

		when(mockScheduleService.getSchedule(1L)).thenReturn(Optional.of(this.schedule));
		
		this.mockMvc
        .perform(delete("/v1/schedule/1")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNoContent());		
	}
	
	@Test
	public void case_delete_schedule_not_found_then_statuscode_404() throws Exception {

		Optional<Schedule> empty = Optional.empty();
		when(mockScheduleService.getSchedule(1L)).thenReturn(empty);

		this.mockMvc
        .perform(delete("/v1/schedule/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isNotFound())
		.andExpect(result -> assertTrue(result.getResolvedException() instanceof ScheduleNotFound));
	}

	@Test
	public void should_return_schedule() throws Exception {

		when(mockScheduleService.getSchedule(1L)).thenReturn(Optional.of(this.schedule));
		
		this.mockMvc
        .perform(get("/v1/schedule/1")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isOk());		
	}

	@Test
	public void case_find_scheduleBy_not_found_then_status_404() throws Exception {

		Optional<Schedule> empty = Optional.empty();

		when(mockScheduleService.getSchedule(1L)).thenReturn(empty);
		
		this.mockMvc
        .perform(get("/v1/schedule/1")
		.content(this.requestSchedule)
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(status().isNotFound());		
	}

}
