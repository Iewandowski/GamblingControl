package com.carol_lewandowski.gambling_control.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@IdClass(PrizeTicket.class)
public class PrizeTicket {
    @Id
    private Long session_id;

    @Id
    private Long sequence;

    @Column
    private int number;

    public PrizeTicket(Long session_id, Long sequence, int number) {
        this.session_id = session_id;
        this.sequence = sequence;
        this.number = number;
    }

    public PrizeTicket() {
    }

}
