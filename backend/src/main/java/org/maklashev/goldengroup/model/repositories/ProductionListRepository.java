package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.production.ProductionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductionListRepository extends JpaRepository<ProductionList, Long> {
    @Query("SELECT id FROM ProductionList WHERE productionStart >= :startDate AND productionFinish <= :endDate")
    List<Integer> findIdsInDateRange(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
