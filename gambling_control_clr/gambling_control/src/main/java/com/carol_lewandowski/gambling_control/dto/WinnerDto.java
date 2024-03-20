package com.carol_lewandowski.gambling_control.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WinnerDto {
    private Long session_id;
    private Long ticket_id;
}
