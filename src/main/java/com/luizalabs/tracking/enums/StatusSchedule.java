package com.luizalabs.tracking.enums;

public enum StatusSchedule {

	SUBMIT(0,"submit"),
	SENT(1,"sent");
	
	private int id;
	private String description;
	
	
	private StatusSchedule(int id, String description) {
		this.id = id;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
