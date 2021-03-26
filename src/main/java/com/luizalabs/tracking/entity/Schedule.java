package com.luizalabs.tracking.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.luizalabs.tracking.dto.RequestScheduleDto;
import com.luizalabs.tracking.enums.StatusSchedule;

@Entity
public class Schedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	private String recipient;
	
	@Enumerated(EnumType.STRING)
	private StatusSchedule status;
	
	private LocalDateTime sendAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Schedule() {
	}

	public Schedule(RequestScheduleDto requestScheduleDto) {
		
		this.message = requestScheduleDto.getMessage();
		this.recipient = requestScheduleDto.getRecipient();
		this.sendAt = requestScheduleDto.getSendAt();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public StatusSchedule getStatus() {
		return status;
	}

	public void setStatus(StatusSchedule status) {
		this.status = status;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@PrePersist
	protected void prePersist() {
	  createdAt = LocalDateTime.now();
	  status = StatusSchedule.SUBMIT;
	}
  
	@PreUpdate
	protected void preUpdate() {
	  updatedAt = LocalDateTime.now();
	}


	
}
