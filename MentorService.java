package com.iips.incubation.service;

import com.iips.incubation.exception.BadRequestException;
import com.iips.incubation.exception.ResourceNotFoundException;
import com.iips.incubation.model.Mentor;
import com.iips.incubation.repository.MentorAllocationRepository;
import com.iips.incubation.repository.MentorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MentorService {

    private final MentorRepository mentorRepository;
    private final MentorAllocationRepository allocationRepository;

    public MentorService(MentorRepository mentorRepository, MentorAllocationRepository allocationRepository) {
        this.mentorRepository = mentorRepository;
        this.allocationRepository = allocationRepository;
    }

    public Mentor create(Mentor mentor) {
        if (mentorRepository.existsByEmail(mentor.getEmail())) {
            throw new BadRequestException("A mentor with this email already exists");
        }
        mentor.setId(null);
        mentor.setActive(true);
        return mentorRepository.save(mentor);
    }

    public List<Mentor> getAll(String expertise) {
        if (expertise == null || expertise.isBlank()) {
            return mentorRepository.findAll();
        }
        return mentorRepository.findByExpertiseContainingIgnoreCase(expertise);
    }

    public Mentor getById(Long id) {
        return mentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentor not found with id " + id));
    }

    public Mentor update(Long id, Mentor updated) {
        Mentor existing = getById(id);
        existing.setName(updated.getName());
        existing.setEmail(updated.getEmail());
        existing.setExpertise(updated.getExpertise());
        existing.setOrganization(updated.getOrganization());
        existing.setActive(updated.isActive());
        return mentorRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        Mentor mentor = getById(id);
        allocationRepository.deleteByMentorId(id);
        mentorRepository.delete(mentor);
    }
}
