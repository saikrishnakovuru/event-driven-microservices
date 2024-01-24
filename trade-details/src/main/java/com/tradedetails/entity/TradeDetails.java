package com.tradedetails.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeDetails {
    private LocalDate date;
    private StockDetails stockDetails;
    private String positionType;
}
