package com.luizalabs.tracking.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.repository.ScheduleRepository;
import com.luizalabs.tracking.service.ScheduleService;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	private final ScheduleRepository scheduleRepository;
	
	@Autowired
	public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
		this.scheduleRepository = scheduleRepository;
	}
	
	@Override
	public Schedule create(RequestScheduleDto scheduleDto) {
		return scheduleRepository.save(new Schedule(scheduleDto));
	}

	@Override
	public void delete(Long id) {
		scheduleRepository.deleteById(id);
	}

	@Override
	public Optional<Schedule> getSchedule(Long id) {
		return scheduleRepository.findById(id);
	}

	@Override
	public List<Schedule> getSchedules() {
		return scheduleRepository.findAll();
	}


}
