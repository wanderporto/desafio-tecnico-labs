package com.luizalabs.tracking.service;

import java.util.List;
import java.util.Optional;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.entity.Schedule;

public interface ScheduleService {

	Schedule create (RequestScheduleDto scheduleDto);
	void delete(Long id);
	Optional<Schedule> getSchedule(Long id);
	List<Schedule> getSchedules();
}
