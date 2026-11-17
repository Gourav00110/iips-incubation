package com.iips.incubation.repository;

import com.iips.incubation.model.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorRepository extends JpaRepository<Mentor, Long> {
    List<Mentor> findByExpertiseContainingIgnoreCase(String expertise);

    boolean existsByEmail(String email);
}
