package org.maklashev.goldengroup.model.controllers;

import java.util.Date;
import java.util.List;

import org.maklashev.goldengroup.model.MyService;
import org.maklashev.goldengroup.model.entity.gypsumboard.GypsumBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GypumBoardController {
    private MyService myService;

    @Autowired
    public GypumBoardController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/api/allboard")
    public List<GypsumBoard> allBoard() {
        List<GypsumBoard> allBoard = myService.getAllGypsumBoards();
        return allBoard;
    }
    @PostMapping("/api/allboard")
    public List<GypsumBoard> boardByDate(@RequestBody Date date) {
        List<GypsumBoard> allBoard = myService.getAllGypsumBoardsByDate(date);
        return allBoard;
    }
}
