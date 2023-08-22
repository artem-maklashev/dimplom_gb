package org.maklashev.goldengroup.model.repositories;

import org.maklashev.goldengroup.model.entity.TradeMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeMarkRepository extends JpaRepository <TradeMark, Long> {
}
