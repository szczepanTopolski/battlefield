package com.sztop.battlefield.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(SimpleMailMessage message){
        try{
            javaMailSender.send(message);
        }catch (MailException e){
            log.error("There was an error during sending an email with title '{}' to {}, cc {}\n {}",
                    message.getSubject(), message.getTo(), message.getCc(), e.getMessage());
        }
    }

}
