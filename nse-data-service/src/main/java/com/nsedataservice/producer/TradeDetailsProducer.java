package com.nsedataservice.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tradedetails.entity.TradeDetails;

@Service
public class TradeDetailsProducer {

    private KafkaTemplate<String, TradeDetails> template;
    @Value("${spring.kafka.topic.name}")
    private String KafkaTopic;

    public TradeDetailsProducer(KafkaTemplate<String, TradeDetails> template) {
        this.template = template;
    }

    public void publishTradeDetails(TradeDetails tradeDetails) {
        if (tradeDetails != null) {
            Message<TradeDetails> message = MessageBuilder
                    .withPayload(tradeDetails)
                    .setHeader(KafkaHeaders.TOPIC, KafkaTopic)
                    .build();
            template.send(message);
        }
    }
}