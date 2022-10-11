package nttdata.grupouno.com.services.impl;

import nttdata.grupouno.com.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, Client> kafkaTemplate;

    public KafkaProducerService(@Qualifier("kafkaTemplateClient") KafkaTemplate<String, Client> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Client client) {
        LOGGER.info("Producing message {}", client);
        this.kafkaTemplate.send("client-topic", client);
    }
}
