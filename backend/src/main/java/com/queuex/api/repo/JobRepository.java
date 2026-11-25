package com.queuex.api.repo;
import com.queuex.api.model.Job; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface JobRepository extends JpaRepository<Job,UUID> { List<Job> findTop100ByOwnerIdOrderByCreatedAtDesc(UUID ownerId); Optional<Job> findByIdAndOwnerId(UUID id,UUID ownerId); }
