package com.edusantanaw.message.infra.consumer;

import com.edusantanaw.message.dtos.NewUserDTO;
import com.edusantanaw.message.services.NewUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class NewUserConsumer {

    private static final Logger log = LoggerFactory.getLogger(NewUserConsumer.class);
    private final NewUserService newUserService;
    private final ObjectMapper objectMapper;

    public NewUserConsumer(NewUserService newUserService) {
        this.newUserService = newUserService;
        this.objectMapper = new ObjectMapper();
    }

    @RabbitListener(queues = "new-user-queue")
    public void consume(String message) {
        try {
            System.out.println(message);
            NewUserDTO newUserDTO = objectMapper.readValue(message, NewUserDTO.class);
            newUserService.exec(newUserDTO);
        } catch (Exception exception) {
            log.error("e: ", exception);
        }
    }
}
