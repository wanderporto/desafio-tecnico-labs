package com.luizalabs.tracking.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
public class RequestScheduleDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Message not be null")
	private String message;
	
	@NotNull(message = "Recipient not be null")
	private String recipient;
	
	@NotNull(message = "Send Date not be null")
	private LocalDateTime sendAt;

	public RequestScheduleDto() {

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

	public LocalDateTime getSendAt() {
		return sendAt;
	}

	public void setSendAt(LocalDateTime sendAt) {
		this.sendAt = sendAt;
	}
	
}
