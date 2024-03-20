package com.carol_lewandowski.gambling_control.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.carol_lewandowski.gambling_control.model.PrizeTicket;
import com.carol_lewandowski.gambling_control.model.Session;
import com.carol_lewandowski.gambling_control.model.Ticket;
import com.carol_lewandowski.gambling_control.model.Winner;
import com.carol_lewandowski.gambling_control.repository.PrizeTicketRepository;
import com.carol_lewandowski.gambling_control.repository.SessionRepository;

@Service
public class SessionService {

    private SessionRepository service_rep;
    private PrizeTicketRepository prize_rep;
    private TicketService ticket_service;
    private WinnerService winner_service;

    @Autowired
    public SessionService(SessionRepository service_rep, PrizeTicketRepository prize_rep, @Lazy TicketService ticket_service, WinnerService winner_service) {
        this.service_rep = service_rep;
        this.prize_rep = prize_rep;
        this.ticket_service = ticket_service;
        this.winner_service = winner_service;

    }

    public Session start(Session session) {
        return service_rep.save(session);
    }

    public void finish() {
        Session old_session = service_rep.findByFinishedIsNull();
        Session new_session = Session.builder()
                                        .session_id(old_session.getSession_id())
                                        .started(old_session.getStarted())
                                        .finished(LocalDateTime.now()).build();
        service_rep.save(new_session);
    }

    public Long findOpenSessionId() {
        try {
            return service_rep.findByFinishedIsNull().getSession_id();
        }catch (NullPointerException e) {
            return 0L;
        }
    }

    public void apuration() {
        int round = 1;
        int plus_rounds = 0;
        boolean winner_found = false;
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets = ticket_service.listAllperSession();
        
        
        for (int i = 0; i < 5; i++) {
            createPrizeTicket(round);
            round++;
        }
        winner_found = checkNumbers(tickets); 
        if (winner_found == false) {
            do {
                createPrizeTicket(round);
                winner_found = checkNumbers(tickets);
                plus_rounds++;
                round++;
            }while(plus_rounds<25 && winner_found != true);     
        }
    }

        public boolean checkNumbers(List<Ticket> tickets) {
            boolean winner_found = false;       
            List<Integer> winningNumbers = new ArrayList<>();
            List<Integer> gambler_numbers = new ArrayList<>();
            for (Ticket ticket : tickets) {
                winningNumbers = prize_rep.findNumberBySessionId(findOpenSessionId());
                gambler_numbers.add(ticket.getNumber1());
                gambler_numbers.add(ticket.getNumber2());
                gambler_numbers.add(ticket.getNumber3());
                gambler_numbers.add(ticket.getNumber4());
                gambler_numbers.add(ticket.getNumber5());

                for (Integer number : winningNumbers) {
                    if (gambler_numbers.contains(number)) {
                        gambler_numbers.remove(Integer.valueOf(number));
                    }
                }
                
                if(gambler_numbers.isEmpty()) {
                    Winner winner = Winner.builder()
                                        .session_id(findOpenSessionId())
                                        .ticket_id(ticket.getTicket_id()).build();
                    winner_service.create(winner);
                    winner_found = true;
                }
                gambler_numbers.clear();
            }
            return winner_found;
        }

        public void createPrizeTicket(int round) {
            Random random = new Random();
            PrizeTicket prize = PrizeTicket.builder()
                                        .session_id(findOpenSessionId())
                                        .sequence(Long.valueOf(round))
                                        .number(random.nextInt(50) + 1).build();
                prize_rep.save(prize);
        }

        public void riggedDice() {
            int round = 1;
            int plus_rounds = 0;
            boolean winner_found = false;
            List<Ticket> tickets = new ArrayList<Ticket>();
            tickets = ticket_service.listAllperSession();
            
            
            for (int i = 0; i < 5; i++) {
                riggedDicePrize(round);
                round++;
            }
            winner_found = checkNumbers(tickets); 
            if (winner_found == false) {
                do {
                    riggedDicePrize(round);
                    winner_found = checkNumbers(tickets);
                    plus_rounds++;
                    round++;
                }while(plus_rounds<25 && winner_found != true);     
            }
        }

        public void riggedDicePrize(int round) {
            PrizeTicket prize = PrizeTicket.builder()
                                        .session_id(findOpenSessionId())
                                        .sequence(Long.valueOf(round))
                                        .number(round).build();
            prize_rep.save(prize);
        }
    }

