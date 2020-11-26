package com.equityposition.domain.dto;

import lombok.Data;

@Data
public class EquityPositionDto {
    private Integer transactionID;
    private Integer tradeID;
    private Integer version;
    private Integer securityCode;
    private Integer quantity;
    private Short action;
    private Short mode;
}
