package com.luizalabs.tracking.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.luizalabs.tracking.entity.Schedule;

public class ResponseScheduleDto {
	
	private Long id;
	private String message;
	private String recipient;
	private String status;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
	private LocalDateTime sendAt;
	
	public ResponseScheduleDto() {
		
	}
	
	public ResponseScheduleDto(Schedule schedule) {
		this.id = schedule.getId();
		this.message = schedule.getMessage();
		this.recipient = schedule.getRecipient();
		this.sendAt = schedule.getSendAt();
		this.status = schedule.getStatus().getDescription();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getRecipient() {
		return recipient;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getSendAt() {
		return sendAt;
	}	
}
