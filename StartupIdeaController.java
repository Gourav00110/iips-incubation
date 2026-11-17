package com.iips.incubation.controller;

import com.iips.incubation.model.IdeaStatus;
import com.iips.incubation.model.StartupIdea;
import com.iips.incubation.service.StartupIdeaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/startups")
public class StartupIdeaController {

    private final StartupIdeaService service;

    public StartupIdeaController(StartupIdeaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<StartupIdea> create(@Valid @RequestBody StartupIdea idea) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(idea));
    }

    @GetMapping
    public List<StartupIdea> getAll(@RequestParam(required = false) IdeaStatus status) {
        return service.getAll(status);
    }

    @GetMapping("/{id}")
    public StartupIdea getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public StartupIdea update(@PathVariable Long id, @Valid @RequestBody StartupIdea idea) {
        return service.update(id, idea);
    }

    @PatchMapping("/{id}/status")
    public StartupIdea updateStatus(@PathVariable Long id, @RequestParam IdeaStatus status) {
        return service.updateStatus(id, status);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Startup idea deleted successfully");
    }
}
