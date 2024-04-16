package com.carol_lewandowski.gambling_control.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carol_lewandowski.gambling_control.dto.TicketDto;
import com.carol_lewandowski.gambling_control.model.Ticket;
import com.carol_lewandowski.gambling_control.service.SessionService;
import com.carol_lewandowski.gambling_control.service.TicketService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private TicketService service;
    private SessionService session;

    @Autowired
    public TicketController(TicketService service, SessionService session) {
        this.service = service;
        this.session = session;
    }

    @PostMapping("/create")
    public ResponseEntity start(@RequestBody TicketDto dto) {
            Ticket ticket = Ticket.builder()
            .session_id(session.findOpenSessionId())
            .cpf(dto.getCpf())
            .name(dto.getName())
            .number1(dto.getNumber1())
            .number2(dto.getNumber2())
            .number3(dto.getNumber3())
            .number4(dto.getNumber4())
            .number5(dto.getNumber5())
            .build();
            Ticket autenticatedTicket = service.create(ticket);
            return ResponseEntity.ok(autenticatedTicket); 
    }

    @PostMapping("/random")
    public ResponseEntity random(@RequestBody TicketDto dto) {
        Random random = new Random();
        Ticket ticket = Ticket.builder()
            .session_id(session.findOpenSessionId())
            .cpf(dto.getCpf())
            .name(dto.getName())
            .number1(random.nextInt(50) + 1)
            .number2(random.nextInt(50) + 1)
            .number3(random.nextInt(50) + 1)
            .number4(random.nextInt(50) + 1)
            .number5(random.nextInt(50) + 1)
            .build();
            Ticket autenticatedTicket = service.create(ticket);
            return ResponseEntity.ok(autenticatedTicket);
    }

    @Transactional
    @GetMapping("/listPerSession")
    public ResponseEntity listAllperSession() {
        List<Ticket> tickets = service.listAllperSession();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/groupNumber")
    public ResponseEntity groupNumbers() {
        List<Object[]> numbers = service.groupBetNumbers();
        return ResponseEntity.ok(numbers);
    }
}
