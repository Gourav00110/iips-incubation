package com.iips.incubation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "incubation_support")
public class IncubationSupport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "startup_idea_id")
    private StartupIdea startupIdea;

    @NotNull(message = "Support type is required")
    @Enumerated(EnumType.STRING)
    private SupportType supportType;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private SupportStatus status;

    private LocalDate requestedDate;

    private LocalDate completedDate;

    public IncubationSupport() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StartupIdea getStartupIdea() {
        return startupIdea;
    }

    public void setStartupIdea(StartupIdea startupIdea) {
        this.startupIdea = startupIdea;
    }

    public SupportType getSupportType() {
        return supportType;
    }

    public void setSupportType(SupportType supportType) {
        this.supportType = supportType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SupportStatus getStatus() {
        return status;
    }

    public void setStatus(SupportStatus status) {
        this.status = status;
    }

    public LocalDate getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(LocalDate requestedDate) {
        this.requestedDate = requestedDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

}
