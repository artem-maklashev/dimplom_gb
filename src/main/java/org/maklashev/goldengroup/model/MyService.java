package org.maklashev.goldengroup.model;


import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.Types;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardType;
import org.maklashev.goldengroup.model.entity.gypsumboard.Thickness;
import org.maklashev.goldengroup.model.entity.gypsumboard.Width;
import org.maklashev.goldengroup.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyService implements JpaService {
    @Autowired
    private final ShiftRepository repository;
    @Autowired
    private final TypesRepository typesRepository;
    @Autowired
    private final TradeMarkRepository tradeMarkRepository;
    @Autowired
    private final BoardTypeRepository boardTypeRepository;

    @Autowired
    private final ThicknessRepository thicknessRepository;

    @Autowired
    private final WidthRepository widthRepository;

    public MyService(ShiftRepository repository, TypesRepository typesRepository, TradeMarkRepository tradeMarkRepository,
                     BoardTypeRepository boardTypeRepository, ThicknessRepository thicknessRepository, WidthRepository widthRepository) {
        this.repository = repository;
        this.typesRepository = typesRepository;
        this.tradeMarkRepository = tradeMarkRepository;
        this.boardTypeRepository = boardTypeRepository;
        this.thicknessRepository = thicknessRepository;
        this.widthRepository = widthRepository;
    }

//    @Autowired
//    public MyService(ShiftRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public List<Shift> getAllShifts() {
        return repository.findAll();
    }

    @Override
    public Shift getShiftById(int id) {
        Optional<Shift> optionalShift = repository.findById(id);
        return optionalShift.orElse(null);
    }

    @Override
    public void SaveShift(Shift shift) {
        repository.save(shift);
    }

    @Override
    public List<Types> getAllTypes() {
        return typesRepository.findAll();
    }

    @Override
    public Types getTypeById(Long id) {
        Optional<Types> optionalTypes = typesRepository.findById(id);
        return optionalTypes.orElse(null);
    }

    @Override
    public void SaveType(Types type) {
        typesRepository.save(type);
    }

    @Override
    public List<TradeMark> getAllTrademark() {
        return tradeMarkRepository.findAll();
    }

    @Override
    public TradeMark getTradeMarkById(int id) {
        Optional<TradeMark> optionalTradeMark = tradeMarkRepository.findById((long) id);
        return optionalTradeMark.orElse(null);
    }

    @Override
    public void SaveTradeMark(TradeMark tradeMark) {
        tradeMarkRepository.save(tradeMark);
    }

    @Override
    public List<BoardType> getAllBoardTypes() {
        return boardTypeRepository.findAll();
    }

    @Override
    public BoardType getBoardTypeById(int id) {
        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
        return optionalBoardType.orElse(null);
    }

    @Override
    public void SaveBoardType(BoardType boardType) {
        boardTypeRepository.save(boardType);
    }

    @Override
    public List<Thickness> getAllThickness() {
        return thicknessRepository.findAll();
    }

    @Override
    public Thickness getThicknessById(int id) {
        Optional<Thickness> optionalThickness = thicknessRepository.findById(id);
        return optionalThickness.orElse(null);
    }

    @Override
    public void SaveThickness(Thickness thickness) {
        thicknessRepository.save(thickness);
    }

    @Override
    public List<Width> getAllWidth() {
        return widthRepository.findAll();
    }

    @Override
    public Width getWidthById(int id) {
        Optional<Width> optionalWidth = widthRepository.findById(id);
        return optionalWidth.orElse(null);
    }

    @Override
    public void SaveWidth(Width width) {
        widthRepository.save(width);
    }
}
