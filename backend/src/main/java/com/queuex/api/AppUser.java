package com.queuex.api.model;
import jakarta.persistence.*; import java.time.Instant; import java.util.UUID;
@Entity @Table(name="app_users") public class AppUser { @Id public UUID id; @Column(unique=true,nullable=false) public String email; @Column(name="password_hash") public String passwordHash; public String role="USER"; @Column(name="created_at") public Instant createdAt; @PrePersist void create(){if(id==null)id=UUID.randomUUID();createdAt=Instant.now();} }
