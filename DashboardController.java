package com.iips.incubation.controller;

import com.iips.incubation.model.FundingStatus;
import com.iips.incubation.model.IdeaStatus;
import com.iips.incubation.repository.FundingRepository;
import com.iips.incubation.repository.MentorAllocationRepository;
import com.iips.incubation.repository.MentorRepository;
import com.iips.incubation.repository.StartupIdeaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final StartupIdeaRepository ideaRepository;
    private final MentorRepository mentorRepository;
    private final MentorAllocationRepository allocationRepository;
    private final FundingRepository fundingRepository;

    public DashboardController(StartupIdeaRepository ideaRepository,
                               MentorRepository mentorRepository,
                               MentorAllocationRepository allocationRepository,
                               FundingRepository fundingRepository) {
        this.ideaRepository = ideaRepository;
        this.mentorRepository = mentorRepository;
        this.allocationRepository = allocationRepository;
        this.fundingRepository = fundingRepository;
    }

    @GetMapping
    public Map<String, Object> summary() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("totalStartups", ideaRepository.count());

        Map<String, Long> byStatus = new LinkedHashMap<>();
        for (IdeaStatus s : IdeaStatus.values()) {
            byStatus.put(s.name(), ideaRepository.countByStatus(s));
        }
        result.put("startupsByStatus", byStatus);

        result.put("totalMentors", mentorRepository.count());
        result.put("activeMentorAllocations", allocationRepository.countByActiveTrue());

        Map<String, Object> funding = new LinkedHashMap<>();
        for (FundingStatus s : FundingStatus.values()) {
            funding.put(s.name(), fundingRepository.sumAmountByStatus(s));
        }
        result.put("fundingByStatus", funding);
        return result;
    }
}
