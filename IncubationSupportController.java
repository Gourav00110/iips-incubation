package com.iips.incubation.controller;

import com.iips.incubation.model.IncubationSupport;
import com.iips.incubation.model.SupportStatus;
import com.iips.incubation.service.IncubationSupportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class IncubationSupportController {

    private final IncubationSupportService service;

    public IncubationSupportController(IncubationSupportService service) {
        this.service = service;
    }

    @PostMapping("/startups/{startupId}/support")
    public ResponseEntity<IncubationSupport> create(@PathVariable Long startupId,
                                                    @Valid @RequestBody IncubationSupport support) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(startupId, support));
    }

    @GetMapping("/support")
    public List<IncubationSupport> getAll() {
        return service.getAll();
    }

    @GetMapping("/startups/{startupId}/support")
    public List<IncubationSupport> getByStartup(@PathVariable Long startupId) {
        return service.getByStartup(startupId);
    }

    @PatchMapping("/support/{id}/status")
    public IncubationSupport updateStatus(@PathVariable Long id, @RequestParam SupportStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/support/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Support request deleted successfully");
    }
}
