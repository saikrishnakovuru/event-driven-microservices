package com.nsedataservice.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.tradedetails.entity.TradeDetails;

@Service
public class TradeDetailsProducer {

    private KafkaTemplate<String, TradeDetails> template;
    private NewTopic topic;

    public TradeDetailsProducer(KafkaTemplate<String, TradeDetails> template, NewTopic topic) {
        this.template = template;
        this.topic = topic;
    }

    public void publishTradeDetails(TradeDetails tradeDetails) {
        if (tradeDetails != null) {
            Message<TradeDetails> message = MessageBuilder
                    .withPayload(tradeDetails)
                    .setHeader(KafkaHeaders.TOPIC, "${spring.kafka.topic.name}")
                    .build();
            System.out.println(tradeDetails.toString());
            template.send(message);
        }
    }
}