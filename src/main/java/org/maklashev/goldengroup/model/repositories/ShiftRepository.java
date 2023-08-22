package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository <Shift, Integer> {
}
