package com.carol_lewandowski.gambling_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carol_lewandowski.gambling_control.service.PrizeTicketService;

@RestController
@RequestMapping("/api/prize")
public class PrizeTicketController {
    private PrizeTicketService service;

    public PrizeTicketController(PrizeTicketService service) {
        this.service = service;
    }

    @GetMapping("/listSorted")
    public ResponseEntity sortedNumbers() {
        List<Integer> numbers = service.listSortedNumbers();
        return ResponseEntity.ok(numbers);
    }

    @GetMapping("/totalSorted")
    public ResponseEntity totalSorted() {
        int total = service.totalSorted();
        return ResponseEntity.ok(total);
    }
}
