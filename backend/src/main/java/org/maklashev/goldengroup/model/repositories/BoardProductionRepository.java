package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardProductionRepository extends JpaRepository<BoardProduction, Long> {

    List<BoardProduction> findAllByGypsumBoardIdIn(List<Integer> gypsumBoardIds);

    List<BoardProduction> findAllByProductionListIdIn(List<Integer> ids);
}
