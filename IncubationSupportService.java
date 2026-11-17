package com.iips.incubation.service;

import com.iips.incubation.exception.ResourceNotFoundException;
import com.iips.incubation.model.IncubationSupport;
import com.iips.incubation.model.StartupIdea;
import com.iips.incubation.model.SupportStatus;
import com.iips.incubation.repository.IncubationSupportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class IncubationSupportService {

    private final IncubationSupportRepository supportRepository;
    private final StartupIdeaService ideaService;

    public IncubationSupportService(IncubationSupportRepository supportRepository, StartupIdeaService ideaService) {
        this.supportRepository = supportRepository;
        this.ideaService = ideaService;
    }

    public IncubationSupport create(Long startupId, IncubationSupport support) {
        StartupIdea idea = ideaService.getById(startupId);
        support.setId(null);
        support.setStartupIdea(idea);
        support.setStatus(SupportStatus.REQUESTED);
        support.setRequestedDate(LocalDate.now());
        support.setCompletedDate(null);
        return supportRepository.save(support);
    }

    public List<IncubationSupport> getAll() {
        return supportRepository.findAll();
    }

    public List<IncubationSupport> getByStartup(Long startupId) {
        ideaService.getById(startupId);
        return supportRepository.findByStartupIdeaId(startupId);
    }

    public IncubationSupport getById(Long id) {
        return supportRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incubation support not found with id " + id));
    }

    public IncubationSupport updateStatus(Long id, SupportStatus status) {
        IncubationSupport support = getById(id);
        support.setStatus(status);
        support.setCompletedDate(status == SupportStatus.COMPLETED ? LocalDate.now() : null);
        return supportRepository.save(support);
    }

    public void delete(Long id) {
        supportRepository.delete(getById(id));
    }
}
