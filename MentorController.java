package com.iips.incubation.controller;

import com.iips.incubation.model.Mentor;
import com.iips.incubation.service.MentorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mentors")
public class MentorController {

    private final MentorService service;

    public MentorController(MentorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mentor> create(@Valid @RequestBody Mentor mentor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(mentor));
    }

    @GetMapping
    public List<Mentor> getAll(@RequestParam(required = false) String expertise) {
        return service.getAll(expertise);
    }

    @GetMapping("/{id}")
    public Mentor getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Mentor update(@PathVariable Long id, @Valid @RequestBody Mentor mentor) {
        return service.update(id, mentor);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        service.delete(id);
        return Map.of("message", "Mentor deleted successfully");
    }
}
