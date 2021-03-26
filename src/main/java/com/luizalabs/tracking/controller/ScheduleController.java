package com.luizalabs.tracking.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.dto.ResponseScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.service.ScheduleService;
import com.luizalabs.tracking.service.ScheduleServiceImpl;


import io.swagger.annotations.Api;

@Api(value = "Schedule")
@Validated
@RestController
@RequestMapping(path = "/v1/schedule")
public class ScheduleController {

	private ScheduleService scheduleService;

	public ScheduleController(ScheduleServiceImpl scheduleServiceImpl) {
		this.scheduleService = scheduleServiceImpl;
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseScheduleDto> getSchedule(@PathVariable("id") Long id) {

		Optional<Schedule> schedule = scheduleService.findById(id);
		if (!schedule.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ResponseScheduleDto>(new ResponseScheduleDto(schedule.get()), HttpStatus.CREATED);
	}

	@PostMapping
	public ResponseEntity<ResponseScheduleDto> createSchedule (@RequestBody @Validated RequestScheduleDto scheduleDto) {

		Schedule schedule = scheduleService.create(new Schedule(scheduleDto));
		return new ResponseEntity<>(new ResponseScheduleDto(schedule), HttpStatus.OK);
		
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
		Optional<Schedule> schedule = scheduleService.findById(id);
		System.out.println(schedule.toString());

		if (!schedule.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		scheduleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
