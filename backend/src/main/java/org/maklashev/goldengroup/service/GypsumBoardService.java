package org.maklashev.goldengroup.service;

import org.apache.el.stream.Stream;
import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.TradeMark;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardType;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.entity.gypsumboard.Thickness;
import org.maklashev.goldengroup.model.entity.gypsumboard.Width;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.model.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GypsumBoardService extends MyService {
    @Autowired
    private final BoardTypeRepository boardTypeRepository;

    @Autowired
    private final ThicknessRepository thicknessRepository;

    @Autowired
    private final WidthRepository widthRepository;

    @Autowired
    private final GypsumBoardRepository gypsumBoardRepository;

    @Autowired
    private final BoardProductionRepository boardProductionRepository;

    @Autowired
    private final ProductionListRepository productionListRepository;

    public GypsumBoardService(ShiftRepository repository, TypesRepository typesRepository, TradeMarkRepository tradeMarkRepository, BoardTypeRepository boardTypeRepository, ThicknessRepository thicknessRepository, WidthRepository widthRepository, GypsumBoardRepository gypsumBoardRepository, BoardProductionRepository boardProductionRepository, ProductionListRepository productionListRepository) {
        super(repository, typesRepository, tradeMarkRepository);
        this.boardTypeRepository = boardTypeRepository;
        this.thicknessRepository = thicknessRepository;
        this.widthRepository = widthRepository;
        this.gypsumBoardRepository = gypsumBoardRepository;
        this.boardProductionRepository = boardProductionRepository;
        this.productionListRepository = productionListRepository;
    }




    public List<BoardType> getAllBoardTypes() {
        return boardTypeRepository.findAll();
    }


    public BoardType getBoardTypeById(int id) {
        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
        return optionalBoardType.orElse(null);
    }


    public void SaveBoardType(BoardType boardType) {
        boardTypeRepository.save(boardType);
    }


    public List<Thickness> getAllThickness() {
        return thicknessRepository.findAll();
    }


    public Thickness getThicknessById(int id) {
        Optional<Thickness> optionalThickness = thicknessRepository.findById(id);
        return optionalThickness.orElse(null);
    }


    public void SaveThickness(Thickness thickness) {
        thicknessRepository.save(thickness);
    }


    public List<Width> getAllWidth() {
        return widthRepository.findAll();
    }


    public Width getWidthById(int id) {
        Optional<Width> optionalWidth = widthRepository.findById(id);
        return optionalWidth.orElse(null);
    }


    public void SaveWidth(Width width) {
        widthRepository.save(width);
    }


    public List<GypsumBoard> getAllGypsumBoards() {
        return gypsumBoardRepository.findAll();
    }


    public List<GypsumBoardProductionData> getAllGypsumBoardsByDate(String startDateValue, String endDateValue) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDate = LocalDate.parse(startDateValue, formatter).atTime(8,0);//
        LocalDateTime endDate = LocalDate.parse(endDateValue, formatter).atTime(8,0);
        System.out.printf("Дата начала: %s, дата конца: %s\n", startDate, endDate);
        List<Integer> ids = productionListRepository.findIdsInDateRange(startDate, endDate);
        System.out.println("Найдено " + ids.size() + "записей из productionLog\n");
        if (ids.size() > 0 ) {
        List<BoardProduction> boardProductions = boardProductionRepository.findAllByProductionListIdIn(ids);
        System.out.println("Получен список из " + boardProductions.size() + " записей");
        return getProductionData(boardProductions);
        }
        return List.of(new GypsumBoardProductionData("Нет данных", 0, 0, 0, 0));
    }
    private List<GypsumBoardProductionData> getProductionData(List<BoardProduction> boardProductions) {
        Map<String, GypsumBoardProductionData> dataMap = new HashMap<>();

        for (BoardProduction bp : boardProductions) {
            float plan = 0, total = 0, fact = 0, defective = 0;
            String name = bp.getGypsumBoard().toString();

            dataMap.putIfAbsent(name, new GypsumBoardProductionData(name, 0, 0, 0, 0));

            if (bp.getGypsumBoardCategory().getId() == 2
                    || bp.getGypsumBoardCategory().getId() == 3
                    || bp.getGypsumBoardCategory().getId() == 4) {
                fact = bp.getValue();
            } else if (bp.getGypsumBoardCategory().getId() == 6) {
                defective = bp.getValue();
            }
            if (bp.getGypsumBoardCategory().getId() == 1) {
                total = bp.getValue();
            }

            GypsumBoardProductionData gypsumBoardProductionData = dataMap.get(name);
            gypsumBoardProductionData.addValues(plan, total, fact, defective);
        }
        System.out.println("Данные для фронтенда - " + dataMap.size() + " записей");
        return new ArrayList<>(dataMap.values());
    }
}


