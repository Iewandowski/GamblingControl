package com.carol_lewandowski.gambling_control.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carol_lewandowski.gambling_control.model.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long>{
    Session findByFinishedIsNull();
}
