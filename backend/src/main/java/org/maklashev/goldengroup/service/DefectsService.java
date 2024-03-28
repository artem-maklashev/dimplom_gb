package org.maklashev.goldengroup.service;

import org.maklashev.goldengroup.model.entity.gypsumboard.BoardDefectsLog;
import org.maklashev.goldengroup.model.entity.production.ProductionList;
import org.maklashev.goldengroup.repositories.ProductionListRepository;
import org.maklashev.goldengroup.repositories.defects.DefectsLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefectsService {
    private final DefectsLogRepository defectsLogRepository;
    private final ProductionListRepository productionListRepository;

    @Autowired
    public DefectsService(DefectsLogRepository defectsLogRepository, ProductionListRepository productionListRepository) {
        this.defectsLogRepository = defectsLogRepository;
        this.productionListRepository = productionListRepository;
    }

    public List<BoardDefectsLog> getDefectsByDate(String startDateString, String endDateString) {
        LocalDateTime startDate = Utils.convertStringToStartOfTheDay(startDateString);
        LocalDateTime endDate = Utils.convertStringToStartOfTheDay(endDateString);
//        List<Long> productionLogIds = productionListRepository.findIdsInDateRange(startDate, endDate);
        List<ProductionList> productionLogIds = productionListRepository.findProductionListByProductionDateBetween(startDate, endDate);
//        return defectsLogRepository.findBoardDefectsLogsByProductionListIdIn(productionLogIds);
        return defectsLogRepository.findBoardDefectsLogsByProductionListIn(productionLogIds);
    }
}
