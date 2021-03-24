package com.luizalabs.tracking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/schedule")
public class ScheduleController {

	@GetMapping
	public String ping() {
		return "pong";
	}
}
