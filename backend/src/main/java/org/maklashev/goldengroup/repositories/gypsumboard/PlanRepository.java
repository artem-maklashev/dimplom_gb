package org.maklashev.goldengroup.repositories.gypsumboard;

import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    @Query("SELECT id FROM Plan WHERE planDate >= :startDate AND planDate < :endDate")
    List<Integer> findIdsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Plan> findPlansByPlanDateBetween(LocalDate startDate, LocalDate endDate);
}
