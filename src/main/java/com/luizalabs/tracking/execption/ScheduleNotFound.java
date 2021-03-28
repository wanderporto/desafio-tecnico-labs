package com.luizalabs.tracking.execption;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ScheduleNotFound extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

	public ScheduleNotFound(String message) {
		super(message);
	}
}