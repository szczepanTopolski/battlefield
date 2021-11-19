package com.sztop.battlefield.mail;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
@Slf4j
@RequiredArgsConstructor
@Profile("scheduler")
public class EmailSendingScheduler {

    private static final String EVERY_MINUTE_CRON_EXPRESSION = "0 * * ? * *";

    private final MailSenderService mailSenderService;
    private final SimpleMailMessageMapper simpleMailMessageMapper;

    @Scheduled(cron = EVERY_MINUTE_CRON_EXPRESSION)
    public void sendEmails(){
        Instant start = Instant.now();
        log.info("Sending scheduler started {}", start);
        Mail mail = new Mail("szczepanPL678@gmail.com",null,
                "Current application 'battlefield' database status",
                "Current database status: TODO","noreply.sztop.battlefield@gmail.com");
        mailSenderService.sendEmail(simpleMailMessageMapper.map(mail));
        Instant end = Instant.now();
        log.info("Sending scheduler ended {} - duration {}s", end, Duration.between(start, end).toSeconds());
    }
}
