package com.carol_lewandowski.gambling_control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carol_lewandowski.gambling_control.model.Session;
import com.carol_lewandowski.gambling_control.service.SessionService;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    private SessionService service;

    @Autowired
    public SessionController(SessionService service) {
        this.service = service;
    }

    @PostMapping("/start")
    public ResponseEntity start() {
        if (service.findOpenSessionId() != 0L) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        Session session = Session.builder().build();
        Session autenticatedSession = service.start(session);
        return ResponseEntity.ok(autenticatedSession);
    }

    @PostMapping("/apuration")
    public ResponseEntity apuration() {
        service.apuration();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/finish")
    public ResponseEntity finish() {
        service.finish();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sessionId")
    public ResponseEntity sessionId() {
        return ResponseEntity.ok(service.findOpenSessionId());
    }

    @PostMapping("/riggedDice")
    public ResponseEntity riggedDice() {
        service.riggedDice();
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
