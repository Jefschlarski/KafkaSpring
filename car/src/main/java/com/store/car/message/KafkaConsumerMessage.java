package com.store.car.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.store.car.dto.CarPostDTO;
import com.store.car.service.CarPostService;

@Component
public class KafkaConsumerMessage {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(KafkaConsumerMessage.class);

    @Autowired
    private CarPostService carPostService; // instanciando o CarPostService

    // KafkaListener fica escutando o topico kafka esperando por eventos
    @KafkaListener(topics = "car-post-topic", groupId = "store-posts-group")
    // espera eventos do tipo CarPostDTO
    public void listening(CarPostDTO carPost) {
        // exibe no terminal o evento consumido
        LOG.info("Received Car Post information: {}", carPost);
        // Passando o evento consumido para o metodo newPostDetails
        carPostService.newPostDetails(carPost);
    }
}
