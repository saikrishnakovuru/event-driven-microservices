package com.nsedataservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nsedataservice.producer.TradeDetailsProducer;
import com.tradedetails.entity.StockDetails;
import com.tradedetails.entity.TradeDetails;

@RestController
public class TradeController {

    private TradeDetailsProducer producer;

    public TradeController(TradeDetailsProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/trade")
    public TradeDetails placeOrder(@RequestBody TradeDetails tradeDetails) {

        StockDetails stockDetails = StockDetails
                .builder()
                .stockName(tradeDetails.getStockDetails().getStockName())
                .price(tradeDetails.getStockDetails().getPrice())
                .quantity(tradeDetails.getStockDetails().getQuantity())
                .build();
        TradeDetails tDetails = TradeDetails
                .builder()
                .date(tradeDetails.getDate())
                .stockDetails(stockDetails)
                .positionType(tradeDetails.getPositionType())
                .build();

        producer.publishTradeDetails(tradeDetails);

        return tDetails;
    }

}
