package com.luizalabs.tracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.luizalabs.tracking.entity.Schedule;
import com.luizalabs.tracking.enums.StatusSchedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    public List<Schedule> findByStatus(StatusSchedule status);
}
