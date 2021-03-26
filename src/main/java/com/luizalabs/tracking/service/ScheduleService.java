package com.luizalabs.tracking.service;

import java.util.Optional;

import com.luizalabs.tracking.entity.Schedule;

public interface ScheduleService {

	Schedule create (Schedule schedule);
	void delete(Long ide);
	Optional<Schedule> findById(Long id);
}
