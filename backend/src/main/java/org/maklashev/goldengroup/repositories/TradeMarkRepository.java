package org.maklashev.goldengroup.repositories;

import org.maklashev.goldengroup.model.entity.TradeMark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeMarkRepository extends JpaRepository <TradeMark, Long> {
}
