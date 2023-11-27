package org.maklashev.goldengroup.model.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DelaysRepository extends JpaRepository<Delays, Integer> {

    List<Delays> findAllByUnitPartIdIn(List<Integer> unitParts);
    List<Delays> findAllByDelayDateBetween(LocalDate start, LocalDate end);
}
