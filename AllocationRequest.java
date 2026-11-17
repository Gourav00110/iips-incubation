package com.iips.incubation.dto;

import jakarta.validation.constraints.NotNull;

public class AllocationRequest {

    @NotNull(message = "mentorId is required")
    private Long mentorId;

    @NotNull(message = "startupIdeaId is required")
    private Long startupIdeaId;

    private String remarks;

    public AllocationRequest() {
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public Long getStartupIdeaId() {
        return startupIdeaId;
    }

    public void setStartupIdeaId(Long startupIdeaId) {
        this.startupIdeaId = startupIdeaId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
