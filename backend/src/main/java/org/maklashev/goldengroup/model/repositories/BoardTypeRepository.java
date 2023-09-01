package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.gypsumboard.BoardType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeRepository extends JpaRepository<BoardType, Integer> {
}
