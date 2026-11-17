package com.iips.incubation.controller;

import com.iips.incubation.dto.AllocationRequest;
import com.iips.incubation.model.MentorAllocation;
import com.iips.incubation.service.MentorAllocationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/allocations")
public class MentorAllocationController {

    private final MentorAllocationService service;

    public MentorAllocationController(MentorAllocationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MentorAllocation> allocate(@Valid @RequestBody AllocationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.allocate(request));
    }

    @GetMapping
    public List<MentorAllocation> getAll() {
        return service.getAll();
    }

    @GetMapping("/mentor/{mentorId}")
    public List<MentorAllocation> getByMentor(@PathVariable Long mentorId) {
        return service.getByMentor(mentorId);
    }

    @GetMapping("/startup/{startupId}")
    public List<MentorAllocation> getByStartup(@PathVariable Long startupId) {
        return service.getByStartup(startupId);
    }

    @PatchMapping("/{id}/release")
    public MentorAllocation release(@PathVariable Long id) {
        return service.release(id);
    }
}
