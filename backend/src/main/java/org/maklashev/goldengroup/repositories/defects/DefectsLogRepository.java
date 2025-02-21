package org.maklashev.goldengroup.repositories.defects;

import org.maklashev.goldengroup.model.entity.gypsumboard.BoardDefectsLog;
import org.maklashev.goldengroup.model.entity.production.ProductionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DefectsLogRepository extends JpaRepository<BoardDefectsLog, Long> {
    List<BoardDefectsLog> findBoardDefectsLogsByProductionListIdIn(List<Long> productionLogIds);
    List<BoardDefectsLog> findBoardDefectsLogsByProductionListIn(List<ProductionList> productionLists);
}
