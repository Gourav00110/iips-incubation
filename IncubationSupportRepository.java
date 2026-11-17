package com.iips.incubation.repository;

import com.iips.incubation.model.IncubationSupport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncubationSupportRepository extends JpaRepository<IncubationSupport, Long> {
    List<IncubationSupport> findByStartupIdeaId(Long startupIdeaId);

    void deleteByStartupIdeaId(Long startupIdeaId);
}
