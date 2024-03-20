package com.carol_lewandowski.gambling_control.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.carol_lewandowski.gambling_control.model.Ticket;
import com.carol_lewandowski.gambling_control.model.Winner;
import com.carol_lewandowski.gambling_control.repository.WinnerRepository;

@Service
public class WinnerService {
    private WinnerRepository repository;
    SessionService session_service;


    public WinnerService(WinnerRepository repository, @Lazy SessionService session_service) {
        this.repository = repository;
        this.session_service = session_service;
    }

    public Winner create(Winner winner) {
        return repository.save(winner);
    }

    public int totalWinners() {
        return repository.totalWinners(session_service.findOpenSessionId());
    }

    public List<Ticket> winnersListPerSession() {
        return repository.winnersList(session_service.findOpenSessionId());
    }

    public boolean checkWin(String cpf) {
        String result = repository.checkWin(cpf);
        System.out.println(result);
        if (result == null) {
            return false;
        }
            return true;
    }
}
