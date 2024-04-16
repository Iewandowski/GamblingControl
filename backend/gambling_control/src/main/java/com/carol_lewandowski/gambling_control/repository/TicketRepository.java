package com.carol_lewandowski.gambling_control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.carol_lewandowski.gambling_control.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long>{
    @Query("select t from Ticket t where t.session_id = ?1")
    List<Ticket> findBySessionId (Long id);

    @Query("SELECT num, COUNT(*) AS count " +
    " FROM ( SELECT number1 AS num FROM Ticket WHERE session_id = ?1 UNION ALL SELECT number2 FROM Ticket WHERE session_id = ?1 " +
    " UNION ALL SELECT number3 FROM Ticket WHERE session_id = ?1 UNION ALL SELECT number4 FROM Ticket WHERE session_id = ?1 UNION ALL" +
    " SELECT number5 FROM Ticket WHERE session_id = ?1 ) AS numbers GROUP BY num ORDER BY count DESC")
    List<Object[]> groupBetNumbers(Long id);
}
