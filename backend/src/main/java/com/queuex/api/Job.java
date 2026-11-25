package com.queuex.api.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="jobs") public class Job {
 @Id public UUID id; @Column(name="owner_id",nullable=false) public UUID ownerId; @Column(name="job_type") public String jobType; @Column(columnDefinition="text") public String payload; public String status; public int priority; public int attempts; @Column(name="max_attempts") public int maxAttempts; @Column(name="scheduled_at") public Instant scheduledAt; @Column(name="created_at") public Instant createdAt; @Column(name="updated_at") public Instant updatedAt; @Column(name="started_at") public Instant startedAt; @Column(name="finished_at") public Instant finishedAt; @Column(columnDefinition="text") public String result; @Column(name="last_error",columnDefinition="text") public String lastError;
 @PrePersist void create(){if(id==null)id=UUID.randomUUID(); if(createdAt==null)createdAt=Instant.now(); if(updatedAt==null)updatedAt=Instant.now();}
}
