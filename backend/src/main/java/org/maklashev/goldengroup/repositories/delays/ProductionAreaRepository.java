package org.maklashev.goldengroup.repositories.delays;

import org.maklashev.goldengroup.model.entity.delays.ProductionArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductionAreaRepository extends JpaRepository<ProductionArea, Integer> {
    List<Integer> findByDivisionId(int id);
}
