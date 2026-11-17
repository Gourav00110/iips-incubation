package com.iips.incubation.repository;

import com.iips.incubation.model.IdeaStatus;
import com.iips.incubation.model.StartupIdea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupIdeaRepository extends JpaRepository<StartupIdea, Long> {
    List<StartupIdea> findByStatus(IdeaStatus status);

    long countByStatus(IdeaStatus status);
}
