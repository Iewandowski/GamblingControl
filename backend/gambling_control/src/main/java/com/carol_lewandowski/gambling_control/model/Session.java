package com.carol_lewandowski.gambling_control.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
public class Session {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long session_id;

    @Column
    private LocalDateTime started;

    @Column
    private LocalDateTime finished;

    public Session(long session_id, LocalDateTime started, LocalDateTime finished) {
        this.session_id = session_id;
        this.started = started;
        this.finished = finished;
    }

    public Session() {
    }

    @PrePersist
    public void prePersist() {
        this.started = LocalDateTime.now();
    }
}
