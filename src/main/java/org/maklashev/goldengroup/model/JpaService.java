package org.maklashev.goldengroup.model;

import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.Types;

import java.util.List;

public interface JpaService {
    //Shift
    List<Shift> getAllShifts();
    Shift getShiftById(int id);
    void SaveShift(Shift shift);

    //Types
    List<Types> getAllTypes();
    Types getTypeById(Long id);
    void SaveType(Types type);

    List<TradeMark> getAllTrademark();
    TradeMark getTradeMarkById(int id);
    void SaveTradeMark(TradeMark tradeMark);

}
