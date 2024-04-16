package com.jdbayer.cuentas.api.services.util.impl;

import com.jdbayer.cuentas.api.services.util.IMailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements IMailService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Override
    public void sendMail(String to, String subject, String body) throws MessagingException {
        baseSendEmail(subject, body, to);
    }

    private void baseSendEmail(String subject, String msg, String emailTo) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, StandardCharsets.UTF_8.name());
        if(emailFrom != null && !emailFrom.isBlank()) {
            helper.setFrom(emailFrom);
            helper.setTo(emailTo);
            helper.setSubject(subject);
            helper.setText(msg);
            emailSender.send(message);
        }
    }
}
