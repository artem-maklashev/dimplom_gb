package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.gypsumboard.*;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.model.repositories.*;
import org.maklashev.goldengroup.model.repositories.gypsumboard.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    @Autowired
    private final PlanRepository planRepository;


    public GypsumBoardService(ShiftRepository repository, TypesRepository typesRepository, TradeMarkRepository tradeMarkRepository, BoardTypeRepository boardTypeRepository, ThicknessRepository thicknessRepository, WidthRepository widthRepository, GypsumBoardRepository gypsumBoardRepository, BoardProductionRepository boardProductionRepository, ProductionListRepository productionListRepository, PlanRepository planRepository) {
        super(repository, typesRepository, tradeMarkRepository);
        this.boardTypeRepository = boardTypeRepository;
        this.thicknessRepository = thicknessRepository;
        this.widthRepository = widthRepository;
        this.gypsumBoardRepository = gypsumBoardRepository;
        this.boardProductionRepository = boardProductionRepository;
        this.productionListRepository = productionListRepository;
        this.planRepository = planRepository;
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


        List<BoardProduction> boardProductions = getBoardProductionByDate(startDateValue, endDateValue);
        List<Plan> planList = getPlanByDate(startDateValue, endDateValue);

        if (!boardProductions.isEmpty() || !planList.isEmpty()) {
            return getProductionData(boardProductions, planList);
        }
        return List.of(new GypsumBoardProductionData("Нет данных", 0, 0, 0, 0));
    }

    private List<GypsumBoardProductionData> getProductionData(List<BoardProduction> boardProductions, List<Plan> planList) {
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

        for (Plan p : planList) {
            float plan = p.getPlanValue();
            String name = p.getGypsumBoard().toString();
            dataMap.putIfAbsent(name, new GypsumBoardProductionData(name, 0, 0, 0, 0));
            GypsumBoardProductionData gypsumBoardProductionData = dataMap.get(name);
            gypsumBoardProductionData.addValues(plan, 0, 0, 0);
        }
        System.out.println("Данные для фронтенда - " + dataMap.size() + " записей");
        List<GypsumBoardProductionData> dataMapValues = new ArrayList<>(dataMap.values());
        dataMapValues.sort(Comparator.comparing(GypsumBoardProductionData::getBoardTitle).thenComparing(GypsumBoardProductionData::getPlanValue));
        return dataMapValues;
    }

    public List<BoardProduction> getBoardProductionByDate(String startDateValue, String endDateValue) {
        LocalDateTime startDate = convertStringToDate(startDateValue);//
        LocalDateTime endDate = convertStringToDate(endDateValue);
        System.out.printf("Дата начала: %s, дата конца: %s\n", startDate, endDate);
        List<Long> ids = productionListRepository.findIdsInDateRange(startDate, endDate);
        System.out.println("Найдено " + ids.size() + "записей из productionLog\n");
        if (!ids.isEmpty()) {
            List<BoardProduction> boardProductions = boardProductionRepository.findAllByProductionListIdIn(ids);
            System.out.println("Получен список из " + boardProductions.size() + " записей");
            return boardProductions;
        }
        return new ArrayList<>();
    }

    public List<Plan> getPlanByDate(String startDateValue, String endDateValue) {
        LocalDateTime startDate = convertStringToDate(startDateValue);//
        LocalDateTime endDate = convertStringToDate(endDateValue);
        List<Integer> planIds = planRepository.findIdsInDateRange(startDate.toLocalDate(), endDate.toLocalDate());
        if (!planIds.isEmpty()) {
           return planRepository.findAllById(planIds);
        }
        return new ArrayList<>();
    }

    public Map<Edge, Float> getEgeQuantityByDate(String startDateValue, String endDateValue) {

        return new HashMap<>();
    }

    private LocalDateTime convertStringToDate(String dateValue){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dateValue, formatter).atTime(8, 0);
    }



}


