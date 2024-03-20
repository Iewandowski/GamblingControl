package com.carol_lewandowski.gambling_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carol_lewandowski.gambling_control.service.WinnerService;

@RestController
@RequestMapping("/api/winner")
public class WinnerController {
    WinnerService winner;

    public WinnerController(WinnerService winner) {
        this.winner = winner;
    }

    @GetMapping("/totalWinner")
    public ResponseEntity totalWinners() {
        int total = winner.totalWinners();
        if(total == 0) {
            return ResponseEntity.ok("There's no winners in this session");
        }
        return ResponseEntity.ok(total);
    }

    @GetMapping("/winnersList") 
    public ResponseEntity winnersList() {
        return ResponseEntity.ok(winner.winnersListPerSession());
    }

    @GetMapping("/checkWin/{cpf}")
    public ResponseEntity checkWin(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(winner.checkWin(cpf));
    }
}
