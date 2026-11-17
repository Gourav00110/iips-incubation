package com.iips.incubation.controller;

import com.iips.incubation.model.Funding;
import com.iips.incubation.model.FundingStatus;
import com.iips.incubation.service.FundingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FundingController {

    private final FundingService service;

    public FundingController(FundingService service) {
        this.service = service;
    }

    @PostMapping("/startups/{startupId}/funding")
    public ResponseEntity<Funding> create(@PathVariable Long startupId, @Valid @RequestBody Funding funding) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(startupId, funding));
    }

    @GetMapping("/funding")
    public List<Funding> getAll() {
        return service.getAll();
    }

    @GetMapping("/startups/{startupId}/funding")
    public List<Funding> getByStartup(@PathVariable Long startupId) {
        return service.getByStartup(startupId);
    }

    @GetMapping("/startups/{startupId}/funding/summary")
    public Map<String, Object> summary(@PathVariable Long startupId) {
        return service.summaryForStartup(startupId);
    }

    @PatchMapping("/funding/{id}/status")
    public Funding updateStatus(@PathVariable Long id, @RequestParam FundingStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/funding/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Funding record deleted successfully");
    }
}
