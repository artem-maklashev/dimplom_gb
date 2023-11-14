package org.maklashev.goldengroup.model.controllers;

import java.util.List;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.outdata.GypsumBoardProductionData;
import org.maklashev.goldengroup.service.GypsumBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GypsumBoardController {
    private final GypsumBoardService gypsumBoardService;

    @Autowired
    public GypsumBoardController(GypsumBoardService gypsumBoardService) {
        this.gypsumBoardService = gypsumBoardService;
    }

    @GetMapping("/allboard")
    public List<GypsumBoardProductionData> allBoard(
            @RequestParam(name = "month", defaultValue = "1") int month,
            @RequestParam(name = "year", defaultValue = "2023") int year) {

        return gypsumBoardService.getAllGypsumBoardsByDate(month, year);
    }
//    @PostMapping("/api/allboard")
//    public List<GypsumBoard> boardByDate(@RequestBody Date date) {
//        List<GypsumBoard> allBoard = myService.getAllGypsumBoardsByDate(date);
//        return allBoard;
//    }
}
