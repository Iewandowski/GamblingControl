package com.carol_lewandowski.gambling_control.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TicketDto {
    private Long session_id;
    private Long ticket_id;
    private String cpf;
    private String name;
    private int number1;
    private int number2;
    private int number3;
    private int number4;
    private int number5; 
}
