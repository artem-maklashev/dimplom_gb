package org.maklashev.goldengroup.model.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DelaysRepository extends JpaRepository<Delays, Integer> {

    List<Delays> findAllByUnitPartIdIn(List<Integer> unitParts);
    List<Delays> findAllByDelayDateBetween(LocalDate startDate, LocalDate endDate);


    List<Delays> findAllByDelayDateStartsWithAndDelayDateEndingWith(LocalDate start, LocalDate end);

    @Query("SELECT id FROM Delays WHERE delayDate >= :startDate AND delayDate <= :endDate")
    List<Integer> findIdsInDateRange(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
