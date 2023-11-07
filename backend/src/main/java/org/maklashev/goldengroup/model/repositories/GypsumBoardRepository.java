package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GypsumBoardRepository extends JpaRepository<GypsumBoard, Integer> {


}
