package org.maklashev.goldengroup.repositories.gypsumboard;

import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardProductionRepository extends JpaRepository<BoardProduction, Integer> {

//    List<BoardProduction> findAllByGypsumBoardIdIn(List<Integer> gypsumBoardIds);

    List<BoardProduction> findAllByProductionListIdIn(List<Long> ids);


}
