package com.carol_lewandowski.gambling_control.model;

import com.carol_lewandowski.gambling_control.utility.WinnerId;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@IdClass(WinnerId.class)
public class Winner {
    @Id
    private Long session_id;

    @Id
    private Long ticket_id;

    public Winner(Long session_id, Long ticket_id) {
        this.session_id = session_id;
        this.ticket_id = ticket_id;
    }

    public Winner() {
    }
}
