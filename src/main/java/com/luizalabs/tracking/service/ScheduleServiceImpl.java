package com.luizalabs.tracking.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.repository.ScheduleRepository;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private ScheduleRepository scheduleRepository;
	
	@Autowired
	public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	
	@Override
	public Schedule create(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	@Override
	public void delete(Long id) {
		scheduleRepository.deleteById(id);
	}

	@Override
	public Optional<Schedule> findById(Long id) {
		return scheduleRepository.findById(id);
	}

}
