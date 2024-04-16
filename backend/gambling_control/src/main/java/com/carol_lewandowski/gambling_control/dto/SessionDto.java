package com.carol_lewandowski.gambling_control.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SessionDto {
    private Long session_id;
    private LocalDateTime date_start;
    private LocalDateTime date_finish;
}
