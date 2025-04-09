package com.edusantanaw.message.infra.mailer.dto;

public record SendMailDTO(String to, String subject, String template) {
}
