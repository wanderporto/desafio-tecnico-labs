package com.luizalabs.tracking.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.dto.ResponseScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.enums.StringToEnumConverter;
import com.luizalabs.tracking.execption.ScheduleNotFound;
import com.luizalabs.tracking.service.ScheduleService;
import com.luizalabs.tracking.service.impl.ScheduleServiceImpl;

import io.swagger.annotations.Api;

@Api(value = "Schedule")
@Validated
@RestController
@RequestMapping(path = "/v1/schedule")
public class ScheduleController {

	private final String MSG_NOT_FOUND = "schedule not found";
	private final ScheduleService scheduleService;

	@Autowired
	public ScheduleController(ScheduleServiceImpl scheduleServiceImpl) {
		this.scheduleService = scheduleServiceImpl;
	}


	@PostMapping
	public ResponseEntity<ResponseScheduleDto> createSchedule(@RequestBody @Validated RequestScheduleDto scheduleDto) {

		Schedule schedule = scheduleService.create(scheduleDto);
		return new ResponseEntity<>(new ResponseScheduleDto(schedule), HttpStatus.CREATED);

	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable("id") Long id) {
		Optional<Schedule> schedule = scheduleService.getScheduleById(id);

		if (!schedule.isPresent()) {
			throw new ScheduleNotFound(MSG_NOT_FOUND);
		}
		scheduleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ResponseScheduleDto> getSchedule(@PathVariable("id") Long id) {

		Optional<Schedule> schedule = scheduleService.getScheduleById(id);

		if(!schedule.isPresent()){
			throw new ScheduleNotFound(MSG_NOT_FOUND);
		}
		
		return new ResponseEntity<ResponseScheduleDto>(new ResponseScheduleDto(schedule.get()), HttpStatus.OK);
	}
	
	
	@GetMapping()
	public ResponseEntity<List<ResponseScheduleDto>> getSchedules() {

		List<Schedule> schedules = scheduleService.getSchedules();
		if (schedules.size() == 0) {
			throw new ScheduleNotFound(MSG_NOT_FOUND);
		}

		List<ResponseScheduleDto> responseSchedules = schedules.stream().map(x -> new ResponseScheduleDto(x))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ResponseScheduleDto>>(responseSchedules, HttpStatus.OK);
	}

	@GetMapping(params = {"status"})
	public ResponseEntity<List<ResponseScheduleDto>> getSchedulesByStatus(@RequestParam String status) {

		List<Schedule> schedules = scheduleService.getSchedulesByStatus(new StringToEnumConverter().convert(status));
		if (schedules.size() == 0) {
			throw new ScheduleNotFound(MSG_NOT_FOUND);
		}

		List<ResponseScheduleDto> responseSchedules = schedules.stream().map(x -> new ResponseScheduleDto(x))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ResponseScheduleDto>>(responseSchedules, HttpStatus.OK);
	}
	
}
