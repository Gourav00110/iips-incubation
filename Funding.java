package com.iips.incubation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "fundings")
public class Funding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "startup_idea_id")
    private StartupIdea startupIdea;

    @NotBlank(message = "Funding source is required")
    private String fundingSource;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be positive")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private FundingType fundingType;

    @Enumerated(EnumType.STRING)
    private FundingStatus status;

    private LocalDate fundingDate;

    private String remarks;

    public Funding() {
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

    public String getFundingSource() {
        return fundingSource;
    }

    public void setFundingSource(String fundingSource) {
        this.fundingSource = fundingSource;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FundingType getFundingType() {
        return fundingType;
    }

    public void setFundingType(FundingType fundingType) {
        this.fundingType = fundingType;
    }

    public FundingStatus getStatus() {
        return status;
    }

    public void setStatus(FundingStatus status) {
        this.status = status;
    }

    public LocalDate getFundingDate() {
        return fundingDate;
    }

    public void setFundingDate(LocalDate fundingDate) {
        this.fundingDate = fundingDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
