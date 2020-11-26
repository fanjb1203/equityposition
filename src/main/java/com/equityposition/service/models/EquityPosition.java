package com.equityposition.service.models;

import lombok.Data;

@Data
public class EquityPosition {
    private Integer transactionID;
    private Integer tradeID;
    private Integer version;
    private Integer securityCode;
    private Integer quantity;
    private Short action;
    private Short mode;
}
