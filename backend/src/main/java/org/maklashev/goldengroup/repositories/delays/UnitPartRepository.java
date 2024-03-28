package org.maklashev.goldengroup.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.UnitPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitPartRepository extends JpaRepository<UnitPart, Integer> {
    List<Integer> findAllByUnitIdIn(List<Integer> ids);
}
