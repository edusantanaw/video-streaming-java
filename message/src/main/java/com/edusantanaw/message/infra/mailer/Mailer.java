package com.edusantanaw.message.infra.mailer;

import com.edusantanaw.message.infra.mailer.dto.SendMailDTO;
import jakarta.mail.MessagingException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class Mailer {
    private final JavaMailSender mailSender;

    Mailer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(SendMailDTO data) throws MessagingException {

        var message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(data.to());
        helper.setSubject(data.subject());
        helper.setText(data.template(), true);
        helper.setFrom("eduardosantanavidal@gmail.com");

        mailSender.send(message);
    }
}
