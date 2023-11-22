package org.maklashev.goldengroup.model.repositories.gypsumboard;

import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Integer> {
    @Query("SELECT id FROM Plan WHERE planDate >= :startDate AND planDate < :endDate")
    List<Integer> findIdsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
