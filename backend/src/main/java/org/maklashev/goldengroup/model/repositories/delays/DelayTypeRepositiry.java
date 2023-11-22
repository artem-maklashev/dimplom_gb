package org.maklashev.goldengroup.model.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.DelayType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DelayTypeRepositiry extends JpaRepository<DelayType, Integer> {
}
