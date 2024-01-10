package org.maklashev.goldengroup.model.controllers;

import java.util.List;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.maklashev.goldengroup.model.entity.gypsumboard.BoardDefectsLog;
import org.maklashev.goldengroup.model.entity.gypsumboard.Plan;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.model.service.DefectsService;
import org.maklashev.goldengroup.model.service.DelaysService;
import org.maklashev.goldengroup.model.service.GypsumBoardService;
import org.maklashev.goldengroup.model.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GypsumBoardController {
    private final GypsumBoardService gypsumBoardService;
    private final DelaysService delaysService;
    private final DefectsService defectsService;
    private final PlanService planService;
    @Autowired
    public GypsumBoardController(GypsumBoardService gypsumBoardService, DelaysService delaysService,
                                 DefectsService defectsService,  PlanService planService) {
        this.gypsumBoardService = gypsumBoardService;
        this.delaysService = delaysService;
        this.defectsService = defectsService;
        this.planService = planService;

    }

    @GetMapping("/allboard")
    public List<GypsumBoardProductionData> allBoard(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate){

        return gypsumBoardService.getAllGypsumBoardsByDate(startDate, endDate);
    }

    @GetMapping("/allboard/production")
    public List<BoardProduction> getProductionData(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate
    ) {
        return gypsumBoardService.getBoardProductionByDate(startDate, endDate);
    }

    @GetMapping("/allboard/delays")
    public List<Delays> getDelays(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate,
            @RequestParam(name = "division", defaultValue = "1") String division
    ) {
        System.out.println("!!!!!");
        System.out.println(startDate);
        System.out.println(endDate);

        return delaysService.getDelaysByDate(startDate, endDate);
    }

    @GetMapping("/allboard/defects")
    public List<BoardDefectsLog> getDefects(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate
    ) {
        return defectsService.getDefectsByDate(startDate, endDate);
    }

    @GetMapping("/planData")
    public List<Plan> getPlan() {
        return planService.getPlanData();
    }
}
