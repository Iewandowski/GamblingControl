package com.carol_lewandowski.gambling_control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carol_lewandowski.gambling_control.repository.PrizeTicketRepository;

@Service
public class PrizeTicketService {
    PrizeTicketRepository repository;
    SessionService session_service;

    @Autowired
    public PrizeTicketService(PrizeTicketRepository repository, SessionService session_service) {
        this.repository = repository;
        this.session_service = session_service;
    }

    public List<Integer> listSortedNumbers() {
        return repository.findNumberBySessionId(session_service.findOpenSessionId());
    }

    public int totalSorted() {
        return repository.totalSortedNumbers(session_service.findOpenSessionId());
    }
}
