package com.iips.incubation.repository;

import com.iips.incubation.model.MentorAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MentorAllocationRepository extends JpaRepository<MentorAllocation, Long> {
    List<MentorAllocation> findByMentorId(Long mentorId);

    List<MentorAllocation> findByStartupIdeaId(Long startupIdeaId);

    boolean existsByMentorIdAndStartupIdeaIdAndActiveTrue(Long mentorId, Long startupIdeaId);

    long countByActiveTrue();

    void deleteByStartupIdeaId(Long startupIdeaId);

    void deleteByMentorId(Long mentorId);
}
