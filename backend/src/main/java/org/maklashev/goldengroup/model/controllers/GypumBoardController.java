package org.maklashev.goldengroup.model.controllers;

import java.util.Date;
import java.util.List;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.maklashev.goldengroup.model.entity.production.BoardProduction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class GypumBoardController {
    private MyService myService;

    @Autowired
    public GypumBoardController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/allboard")
    public List<BoardProduction> allBoard(
            @RequestParam(name = "month", defaultValue = "1") int month,
            @RequestParam(name = "year", defaultValue = "2023") int year) {

        return myService.getAllGypsumBoardsByDate(month, year);
    }
//    @PostMapping("/api/allboard")
//    public List<GypsumBoard> boardByDate(@RequestBody Date date) {
//        List<GypsumBoard> allBoard = myService.getAllGypsumBoardsByDate(date);
//        return allBoard;
//    }
}
