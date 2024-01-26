package com.upstoxservice.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.tradedetails.entity.TradeDetails;

@Service
public class StockDetailsConsumerUpstox {

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeStockDetailsFromNSEService(TradeDetails tradeDetails) {
        System.out.println("In Upstox " + tradeDetails.toString());
    }

}
