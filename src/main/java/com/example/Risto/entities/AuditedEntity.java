package com.example.Risto.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity {

	@Column(name = "created_at", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime createdAt;
	
	@Column(name = "updated_at", nullable = false)
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
