package com.iips.incubation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "mentor_allocations")
public class MentorAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "startup_idea_id")
    private StartupIdea startupIdea;

    private LocalDate allocatedDate;

    private String remarks;

    private boolean active = true;

    public MentorAllocation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }

    public StartupIdea getStartupIdea() {
        return startupIdea;
    }

    public void setStartupIdea(StartupIdea startupIdea) {
        this.startupIdea = startupIdea;
    }

    public LocalDate getAllocatedDate() {
        return allocatedDate;
    }

    public void setAllocatedDate(LocalDate allocatedDate) {
        this.allocatedDate = allocatedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
