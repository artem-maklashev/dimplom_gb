package org.maklashev.goldengroup.model;


import org.maklashev.goldengroup.model.entity.Shift;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.Types;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardType;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.entity.gypsumboard.Thickness;
import org.maklashev.goldengroup.model.entity.gypsumboard.Width;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyService implements JpaService {
    @Autowired
    private final ShiftRepository repository;
    @Autowired
    private final TypesRepository typesRepository;
    @Autowired
    private final TradeMarkRepository tradeMarkRepository;



    public MyService(ShiftRepository repository, TypesRepository typesRepository, TradeMarkRepository tradeMarkRepository) {
        this.repository = repository;
        this.typesRepository = typesRepository;
        this.tradeMarkRepository = tradeMarkRepository;
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
    public Types getTypeById(int id) {
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
}
