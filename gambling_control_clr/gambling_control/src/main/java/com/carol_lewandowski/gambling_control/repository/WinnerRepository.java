package com.carol_lewandowski.gambling_control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carol_lewandowski.gambling_control.model.Ticket;
import com.carol_lewandowski.gambling_control.model.Winner;

@Repository
public interface WinnerRepository extends JpaRepository<Winner, Long> {
    @Query("select count(coalesce(w.ticket_id,0)) from Winner w where w.session_id = ?1")
    int totalWinners(Long id);

    @Query("select t from Ticket t where t.session_id = ?1 and t.ticket_id in (select w.ticket_id from Winner w where w.session_id = ?1) ORDER BY t.name ASC")
    List<Ticket> winnersList(Long id);

    @Query("SELECT CASE WHEN EXISTS (SELECT w FROM Winner w, Ticket t WHERE w.ticket_id = t.ticket_id and t.cpf = ?1) THEN 'true' END")
    String checkWin(String cpf);
}
