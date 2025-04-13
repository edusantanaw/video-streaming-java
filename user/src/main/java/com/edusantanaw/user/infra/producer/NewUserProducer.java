package com.edusantanaw.user.infra.producer;

import com.edusantanaw.user.domain.dto.UserResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

@Component
public class NewUserProducer {
    private final AmqpTemplate amqpTemplate;
    private final ObjectMapper objectMapper;

    public NewUserProducer(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void exec(UserResponseDTO user) throws JsonProcessingException {
            amqpTemplate.convertAndSend(
                    "new-user-exchange",
                    "new-user-route-key",
                    objectMapper.writeValueAsString(user)
            );
    }
}
