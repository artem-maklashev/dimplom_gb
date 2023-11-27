package org.maklashev.goldengroup.model.controllers;

import java.util.List;

import org.maklashev.goldengroup.model.entity.delays.Delays;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.service.DelaysService;
import org.maklashev.goldengroup.service.GypsumBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GypsumBoardController {
    private final GypsumBoardService gypsumBoardService;
    private final DelaysService delaysService;

    @Autowired
    public GypsumBoardController(GypsumBoardService gypsumBoardService, DelaysService delaysService) {
        this.gypsumBoardService = gypsumBoardService;
        this.delaysService = delaysService;
    }

    @GetMapping("/allboard")
    public List<GypsumBoardProductionData> allBoard(
//            @RequestParam(name = "day", defaultValue = "1") int day,
//            @RequestParam(name = "month", defaultValue = "1") int month,
//            @RequestParam(name = "year", defaultValue = "2023") int year
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
//    @PostMapping("/api/allboard")
//    public List<GypsumBoard> boardByDate(@RequestBody Date date) {
//        List<GypsumBoard> allBoard = myService.getAllGypsumBoardsByDate(date);
//        return allBoard;
//    }
    @GetMapping("/allboard/delays")
    public List<Delays> getDelays(
            @RequestParam(name = "startDate", defaultValue = "2023-01-01") String startDate,
            @RequestParam(name = "endDate", defaultValue = "2023-01-01") String endDate,
            @RequestParam(name = "division", defaultValue = "1") String division
    ) {
        return delaysService.getDelaysByDate(startDate, endDate);
    }
}
