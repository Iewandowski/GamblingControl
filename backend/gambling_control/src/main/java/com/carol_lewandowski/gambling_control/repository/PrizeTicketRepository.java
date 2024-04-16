package com.carol_lewandowski.gambling_control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.carol_lewandowski.gambling_control.model.PrizeTicket;

@Repository
public interface PrizeTicketRepository extends JpaRepository<PrizeTicket,Long>{
    @Query("select p.number from PrizeTicket p where p.session_id = ?1")
    List<Integer> findNumberBySessionId(Long id);

    @Query("select count(p.number) from PrizeTicket p where p.session_id = ?1")
    int totalSortedNumbers(Long id);

    @Query("select p.number, count(p.number) from PrizeTicket p where p.session_id = ?1 group by p.number")
    List<Object[]> groupBets();
}
