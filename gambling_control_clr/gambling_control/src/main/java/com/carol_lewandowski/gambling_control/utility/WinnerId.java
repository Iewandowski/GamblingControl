package com.carol_lewandowski.gambling_control.utility;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WinnerId implements Serializable{
    private Long session_id;
    private Long ticket_id;
}
