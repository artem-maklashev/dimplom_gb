package org.maklashev.goldengroup.model;

import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.Types;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardType;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.entity.gypsumboard.Thickness;
import org.maklashev.goldengroup.model.entity.gypsumboard.Width;

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

    //TradeMark
    List<TradeMark> getAllTrademark();
    TradeMark getTradeMarkById(int id);
    void SaveTradeMark(TradeMark tradeMark);

    //BoardType
    List<BoardType> getAllBoardTypes();
    BoardType getBoardTypeById(int id);
    void SaveBoardType(BoardType boardType);

    //Thickness
    List<Thickness> getAllThickness();
    Thickness getThicknessById(int id);
    void SaveThickness(Thickness thickness);

    //Width
    List<Width> getAllWidth();
    Width getWidthById(int id);
    void SaveWidth(Width width);

    //Length
    //TO DO

    //GypsumBoard
    List<GypsumBoard> getAllGypsumBoards();
}
