package com.iips.incubation.repository;

import com.iips.incubation.model.Funding;
import com.iips.incubation.model.FundingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface FundingRepository extends JpaRepository<Funding, Long> {
    List<Funding> findByStartupIdeaId(Long startupIdeaId);

    void deleteByStartupIdeaId(Long startupIdeaId);

    @Query("select coalesce(sum(f.amount), 0) from Funding f where f.status = :status")
    BigDecimal sumAmountByStatus(@Param("status") FundingStatus status);

    @Query("select coalesce(sum(f.amount), 0) from Funding f where f.startupIdea.id = :ideaId and f.status = :status")
    BigDecimal sumAmountByStartupAndStatus(@Param("ideaId") Long ideaId, @Param("status") FundingStatus status);
}
