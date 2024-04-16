package com.carol_lewandowski.gambling_control.model;

import com.carol_lewandowski.gambling_control.utility.TicketId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@IdClass(TicketId.class)
public class Ticket {
    @Id
    private Long session_id;

    @Id
    @SequenceGenerator(name = "TicketSequence", initialValue = 1000)
    @GeneratedValue(generator = "TicketSequence")
    private Long ticket_id;

    @Column
    private String cpf;

    @Column
    private String name;

    @Column
    private int number1;

    @Column
    private int number2;

    @Column
    private int number3;

    @Column
    private int number4;

    @Column
    private int number5;

    public Ticket(Long session_id, Long ticket_id, String cpf, String name, int number1, int number2, int number3,
            int number4, int number5) {
        this.session_id = session_id;
        this.ticket_id = ticket_id;
        this.cpf = cpf;
        this.name = name;
        this.number1 = number1;
        this.number2 = number2;
        this.number3 = number3;
        this.number4 = number4;
        this.number5 = number5;
    }

    public Ticket() {
    }
}
