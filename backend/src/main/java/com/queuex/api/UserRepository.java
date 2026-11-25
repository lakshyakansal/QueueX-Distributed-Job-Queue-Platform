package com.queuex.api.repo; import com.queuex.api.model.AppUser; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface UserRepository extends JpaRepository<AppUser,UUID>{Optional<AppUser> findByEmail(String email);}
