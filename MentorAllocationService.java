package com.iips.incubation.service;

import com.iips.incubation.dto.AllocationRequest;
import com.iips.incubation.exception.BadRequestException;
import com.iips.incubation.exception.ResourceNotFoundException;
import com.iips.incubation.model.Mentor;
import com.iips.incubation.model.MentorAllocation;
import com.iips.incubation.model.StartupIdea;
import com.iips.incubation.repository.MentorAllocationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MentorAllocationService {

    private final MentorAllocationRepository allocationRepository;
    private final MentorService mentorService;
    private final StartupIdeaService ideaService;

    public MentorAllocationService(MentorAllocationRepository allocationRepository,
                                   MentorService mentorService,
                                   StartupIdeaService ideaService) {
        this.allocationRepository = allocationRepository;
        this.mentorService = mentorService;
        this.ideaService = ideaService;
    }

    public MentorAllocation allocate(AllocationRequest request) {
        Mentor mentor = mentorService.getById(request.getMentorId());
        StartupIdea idea = ideaService.getById(request.getStartupIdeaId());

        if (!mentor.isActive()) {
            throw new BadRequestException("Mentor is not active");
        }
        if (allocationRepository.existsByMentorIdAndStartupIdeaIdAndActiveTrue(mentor.getId(), idea.getId())) {
            throw new BadRequestException("This mentor is already allocated to this startup");
        }

        MentorAllocation allocation = new MentorAllocation();
        allocation.setMentor(mentor);
        allocation.setStartupIdea(idea);
        allocation.setRemarks(request.getRemarks());
        allocation.setAllocatedDate(LocalDate.now());
        allocation.setActive(true);
        return allocationRepository.save(allocation);
    }

    public List<MentorAllocation> getAll() {
        return allocationRepository.findAll();
    }

    public List<MentorAllocation> getByMentor(Long mentorId) {
        mentorService.getById(mentorId);
        return allocationRepository.findByMentorId(mentorId);
    }

    public List<MentorAllocation> getByStartup(Long startupId) {
        ideaService.getById(startupId);
        return allocationRepository.findByStartupIdeaId(startupId);
    }

    public MentorAllocation release(Long id) {
        MentorAllocation allocation = allocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Allocation not found with id " + id));
        allocation.setActive(false);
        return allocationRepository.save(allocation);
    }
}
