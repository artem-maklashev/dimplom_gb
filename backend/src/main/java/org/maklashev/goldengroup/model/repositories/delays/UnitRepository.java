package org.maklashev.goldengroup.model.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Integer> findAllByProductionAreaIdIn(List<Integer> ids);
}
