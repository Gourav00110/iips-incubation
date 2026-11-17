package com.iips.incubation.service;

import com.iips.incubation.exception.ResourceNotFoundException;
import com.iips.incubation.model.Funding;
import com.iips.incubation.model.FundingStatus;
import com.iips.incubation.model.StartupIdea;
import com.iips.incubation.repository.FundingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class FundingService {

    private final FundingRepository fundingRepository;
    private final StartupIdeaService ideaService;

    public FundingService(FundingRepository fundingRepository, StartupIdeaService ideaService) {
        this.fundingRepository = fundingRepository;
        this.ideaService = ideaService;
    }

    public Funding create(Long startupId, Funding funding) {
        StartupIdea idea = ideaService.getById(startupId);
        funding.setId(null);
        funding.setStartupIdea(idea);
        funding.setStatus(FundingStatus.APPLIED);
        if (funding.getFundingDate() == null) {
            funding.setFundingDate(LocalDate.now());
        }
        return fundingRepository.save(funding);
    }

    public List<Funding> getAll() {
        return fundingRepository.findAll();
    }

    public List<Funding> getByStartup(Long startupId) {
        ideaService.getById(startupId);
        return fundingRepository.findByStartupIdeaId(startupId);
    }

    public Funding getById(Long id) {
        return fundingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Funding record not found with id " + id));
    }

    public Funding updateStatus(Long id, FundingStatus status) {
        Funding funding = getById(id);
        funding.setStatus(status);
        return fundingRepository.save(funding);
    }

    public Map<String, Object> summaryForStartup(Long startupId) {
        ideaService.getById(startupId);
        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("startupId", startupId);
        for (FundingStatus s : FundingStatus.values()) {
            BigDecimal total = fundingRepository.sumAmountByStartupAndStatus(startupId, s);
            summary.put(s.name().toLowerCase() + "Amount", total);
        }
        return summary;
    }

    public void delete(Long id) {
        fundingRepository.delete(getById(id));
    }
}
