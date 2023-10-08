package com.analytics.data.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.service.PostAnalyticsService;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumerMessage.class);
    @Autowired
    private PostAnalyticsService postAnalyticsService;

    @KafkaListener(topics = "car-post-topic", groupId = "analytics-posts-group")
    public void listening(@Payload CarPostDTO carPost) {
        System.out.println("Received Car Post Information: " + carPost);
        LOG.info("Analytics Data Received Car Post Information: {}", carPost);
        postAnalyticsService.saveDataAnalytics(carPost);
    }
}
