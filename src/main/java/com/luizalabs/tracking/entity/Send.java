package com.luizalabs.tracking.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;

import com.luizalabs.tracking.enums.TypeComunnicationEnum;


@Entity
public class Send {

	@Id
    @SequenceGenerator(name = "send_seq", sequenceName = "send_seq", allocationSize = 1)
    @GeneratedValue(generator = "send_seq", strategy = GenerationType.AUTO)
	private Long id;
	
	@Enumerated(value = EnumType.STRING)
	private TypeComunnicationEnum typeCommunication;

	private LocalDateTime sentAt;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	private Schedule schedule;

	public Send() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TypeComunnicationEnum getTypeCommunication() {
		return typeCommunication;
	}

	public void setTypeCommunication(TypeComunnicationEnum typeCommunication) {
		this.typeCommunication = typeCommunication;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getSentAt() {
		return sentAt;
	}

	public void setSentAt(LocalDateTime sentAt) {
		this.sentAt = sentAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	@PrePersist
	protected void prePersist() {
	  createdAt = LocalDateTime.now();
	}
  
	@PreUpdate
	protected void preUpdate() {
	  updatedAt = LocalDateTime.now();
	}
}
