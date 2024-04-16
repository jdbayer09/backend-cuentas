package com.jdbayer.cuentas.api.services.util;

import jakarta.mail.MessagingException;

public interface IMailService {

    void sendMail(String to, String subject, String body) throws MessagingException;
}
