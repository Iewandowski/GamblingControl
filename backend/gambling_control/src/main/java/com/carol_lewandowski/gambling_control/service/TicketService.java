package com.carol_lewandowski.gambling_control.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carol_lewandowski.gambling_control.exception.DefaultException;
import com.carol_lewandowski.gambling_control.model.Ticket;
import com.carol_lewandowski.gambling_control.repository.TicketRepository;

@Service
public class TicketService {
    private TicketRepository repository;
    private SessionService session;

    @Autowired
    public TicketService(TicketRepository repository, SessionService session) {
        this.repository = repository;
        this.session = session;
    }

    public Ticket create(Ticket ticket) {
        if ((ticket.getNumber1() < 1 && ticket.getNumber1() > 50)
            || (ticket.getNumber2() < 1 && ticket.getNumber2() > 50)
            || (ticket.getNumber3() < 1 && ticket.getNumber3() > 50)
            || (ticket.getNumber4() < 1 && ticket.getNumber4() > 50)
            || (ticket.getNumber5() < 1 && ticket.getNumber5() > 50)) {
                throw new DefaultException("You need to choose numbers between 1 and 50");
            }
        return repository.save(ticket);
    }

    public List<Ticket> listAllperSession() {
        try {
            List<Ticket> allTickets = new ArrayList<>();
            allTickets = repository.findBySessionId(session.findOpenSessionId());
            return allTickets;
        }catch(NullPointerException e) {
            throw new DefaultException("There not tickets for this current session");
        }  
    }

    public List<Object[]> groupBetNumbers() {
        return repository.groupBetNumbers(session.findOpenSessionId());
    }
}
