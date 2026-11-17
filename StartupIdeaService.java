package com.iips.incubation.service;

import com.iips.incubation.exception.ResourceNotFoundException;
import com.iips.incubation.model.IdeaStatus;
import com.iips.incubation.model.StartupIdea;
import com.iips.incubation.repository.FundingRepository;
import com.iips.incubation.repository.IncubationSupportRepository;
import com.iips.incubation.repository.MentorAllocationRepository;
import com.iips.incubation.repository.StartupIdeaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class StartupIdeaService {

    private final StartupIdeaRepository ideaRepository;
    private final IncubationSupportRepository supportRepository;
    private final MentorAllocationRepository allocationRepository;
    private final FundingRepository fundingRepository;

    public StartupIdeaService(StartupIdeaRepository ideaRepository,
                              IncubationSupportRepository supportRepository,
                              MentorAllocationRepository allocationRepository,
                              FundingRepository fundingRepository) {
        this.ideaRepository = ideaRepository;
        this.supportRepository = supportRepository;
        this.allocationRepository = allocationRepository;
        this.fundingRepository = fundingRepository;
    }

    public StartupIdea create(StartupIdea idea) {
        idea.setId(null);
        idea.setStatus(IdeaStatus.SUBMITTED);
        idea.setSubmittedDate(LocalDate.now());
        return ideaRepository.save(idea);
    }

    public List<StartupIdea> getAll(IdeaStatus status) {
        return status == null ? ideaRepository.findAll() : ideaRepository.findByStatus(status);
    }

    public StartupIdea getById(Long id) {
        return ideaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Startup idea not found with id " + id));
    }

    public StartupIdea update(Long id, StartupIdea updated) {
        StartupIdea existing = getById(id);
        existing.setTitle(updated.getTitle());
        existing.setDescription(updated.getDescription());
        existing.setDomain(updated.getDomain());
        existing.setFounderName(updated.getFounderName());
        existing.setFounderEmail(updated.getFounderEmail());
        existing.setTeamMembers(updated.getTeamMembers());
        return ideaRepository.save(existing);
    }

    public StartupIdea updateStatus(Long id, IdeaStatus status) {
        StartupIdea existing = getById(id);
        existing.setStatus(status);
        return ideaRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        StartupIdea existing = getById(id);
        supportRepository.deleteByStartupIdeaId(id);
        allocationRepository.deleteByStartupIdeaId(id);
        fundingRepository.deleteByStartupIdeaId(id);
        ideaRepository.delete(existing);
    }
}
