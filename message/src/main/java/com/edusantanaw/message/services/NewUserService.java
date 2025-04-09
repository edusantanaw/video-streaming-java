package com.edusantanaw.message.services;

import com.edusantanaw.message.dtos.NewUserDTO;
import com.edusantanaw.message.infra.entities.UserEntity;
import com.edusantanaw.message.infra.mailer.Mailer;
import com.edusantanaw.message.infra.mailer.dto.SendMailDTO;
import com.edusantanaw.message.infra.repositories.UserRepository;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.Paths;

@Service
public class NewUserService {
    protected UserRepository userRepository;
    protected Mailer mailer;

    public NewUserService(UserRepository userRepository, Mailer mailer) {
        this.userRepository = userRepository;
        this.mailer = mailer;
    }

    public void exec(NewUserDTO data) throws IOException, MessagingException {
        UserEntity user = new UserEntity(data.id(), data.name(), data.email());
        userRepository.save(user);
        sendWelcomeMail(data);
    }

    public void sendWelcomeMail(NewUserDTO data) throws IOException, MessagingException {
        String template = buildTemplate(data.name());
        SendMailDTO sendMailDTO = new SendMailDTO(data.email(), "Bem-vindo", template);
        mailer.send(sendMailDTO);
    }

    protected String buildTemplate(String name) throws IOException {
        String template = getTemplate();
        return template.replace("[{NOME}]", name);
    }

    private String getTemplate() throws IOException {
        return Files.readString(Paths.get("public/NewUserTemplate.html"));
    }
}
