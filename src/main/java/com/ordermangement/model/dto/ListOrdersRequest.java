package com.ordermangement.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListOrdersRequest {
    private String customerUid;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
