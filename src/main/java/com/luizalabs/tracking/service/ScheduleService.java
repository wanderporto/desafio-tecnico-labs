package com.luizalabs.tracking.service;

import java.util.List;
import java.util.Optional;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.enums.StatusSchedule;

public interface ScheduleService {

	Schedule create (RequestScheduleDto scheduleDto);

	void delete(Long id);

	Optional<Schedule> getScheduleById(Long id);

	List<Schedule> getSchedulesByStatus(StatusSchedule status);
	
	List<Schedule> getSchedules();
}