package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.entity.gypsumboard.*;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.entity.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.repositories.ProductionListRepository;
import org.maklashev.goldengroup.repositories.ShiftRepository;
import org.maklashev.goldengroup.repositories.TradeMarkRepository;
import org.maklashev.goldengroup.repositories.TypesRepository;
import org.maklashev.goldengroup.repositories.gypsumboard.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class GypsumBoardService extends MyService {
    private final BoardTypeRepository boardTypeRepository;
    private final ThicknessRepository thicknessRepository;
    private final WidthRepository widthRepository;
    private final GypsumBoardRepository gypsumBoardRepository;
    private final BoardProductionRepository boardProductionRepository;
    private final ProductionListRepository productionListRepository;
    private final PlanRepository planRepository;

    @Autowired
    public GypsumBoardService(ShiftRepository repository, TypesRepository typesRepository,
                              TradeMarkRepository tradeMarkRepository, BoardTypeRepository boardTypeRepository,
                              ThicknessRepository thicknessRepository, WidthRepository widthRepository,
                              GypsumBoardRepository gypsumBoardRepository, BoardProductionRepository boardProductionRepository,
                              ProductionListRepository productionListRepository, PlanRepository planRepository) {
        super(repository, typesRepository, tradeMarkRepository);
        this.boardTypeRepository = boardTypeRepository;
        this.thicknessRepository = thicknessRepository;
        this.widthRepository = widthRepository;
        this.gypsumBoardRepository = gypsumBoardRepository;
        this.boardProductionRepository = boardProductionRepository;
        this.productionListRepository = productionListRepository;
        this.planRepository = planRepository;

    }


//    public List<BoardType> getAllBoardTypes() {
//        return boardTypeRepository.findAll();
//    }
//
//
//    public BoardType getBoardTypeById(int id) {
//        Optional<BoardType> optionalBoardType = boardTypeRepository.findById(id);
//        return optionalBoardType.orElse(null);
//    }
//
//
//    public void SaveBoardType(BoardType boardType) {
//        boardTypeRepository.save(boardType);
//    }
//
//
//    public List<Thickness> getAllThickness() {
//        return thicknessRepository.findAll();
//    }
//
//
//    public Thickness getThicknessById(int id) {
//        Optional<Thickness> optionalThickness = thicknessRepository.findById(id);
//        return optionalThickness.orElse(null);
//    }
//
//
//    public void SaveThickness(Thickness thickness) {
//        thicknessRepository.save(thickness);
//    }
//
//
//    public List<Width> getAllWidth() {
//        return widthRepository.findAll();
//    }
//
//
//    public Width getWidthById(int id) {
//        Optional<Width> optionalWidth = widthRepository.findById(id);
//        return optionalWidth.orElse(null);
//    }
//
//
//    public void SaveWidth(Width width) {
//        widthRepository.save(width);
//    }
//
//
//    public List<GypsumBoard> getAllGypsumBoards() {
//        return gypsumBoardRepository.findAll();
//    }


    public List<GypsumBoardProductionData> getAllGypsumBoardsByDate(String startDateValue, String endDateValue) {


        List<BoardProduction> boardProductions = getBoardProductionByDate(startDateValue, endDateValue);
        List<Plan> planList = getPlanByDate(startDateValue, endDateValue);

        if (!boardProductions.isEmpty() || !planList.isEmpty()) {
            return getProductionData(boardProductions, planList);
        }
        return List.of(new GypsumBoardProductionData("Нет данных", 0, 0, 0, 0));
    }

    public List<GypsumBoardProductionData> getProductionData(List<BoardProduction> boardProductions, List<Plan> planList) {
        Map<String, GypsumBoardProductionData> dataMap = new HashMap<>();
        if (!boardProductions.isEmpty() ||!planList.isEmpty()) {
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
        }
        if(!planList.isEmpty()) {
            for (Plan p : planList) {
                float plan = p.getPlanValue();
                String name = p.getGypsumBoard().toString();
                dataMap.putIfAbsent(name, new GypsumBoardProductionData(name, 0, 0, 0, 0));
                GypsumBoardProductionData gypsumBoardProductionData = dataMap.get(name);
                gypsumBoardProductionData.addValues(plan, 0, 0, 0);
            }
        }
        System.out.println("Данные для фронтенда - " + dataMap.size() + " записей");
        List<GypsumBoardProductionData> dataMapValues = new ArrayList<>(dataMap.values());
        dataMapValues.sort(Comparator.comparing(GypsumBoardProductionData::getBoardTitle).thenComparing(GypsumBoardProductionData::getPlanValue));
        return dataMapValues;
    }

    public List<BoardProduction> getBoardProductionByDate(String startDateValue, String endDateValue) {
        LocalDateTime startDate = Utils.convertStringToDate(startDateValue);//
        System.out.println("Дата начала: " + startDate);
        LocalDateTime endDate = Utils.convertStringToDate(endDateValue);
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
        LocalDateTime startDate = Utils.convertStringToDate(startDateValue);//
        LocalDateTime endDate = Utils.convertStringToDate(endDateValue);
        List<Integer> planIds = planRepository.findIdsInDateRange(startDate.toLocalDate(), endDate.toLocalDate());
        if (!planIds.isEmpty()) {
           return planRepository.findAllById(planIds);
        }
        return new ArrayList<>();
    }







}


