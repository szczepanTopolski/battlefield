package com.sztop.battlefield.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SimpleMailMessageMapperImpl implements SimpleMailMessageMapper{

    private final MapperUtils mapperUtils;

    @Override
    public SimpleMailMessage map(Mail mail) {
        if(mail == null) return null;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setCc(mapperUtils.splitString(mail.getCc()));
        simpleMailMessage.setFrom(mail.getFromAddr());
        simpleMailMessage.setText(mail.getContent());
        simpleMailMessage.setTo(mapperUtils.splitString(mail.getRecipent()));
        simpleMailMessage.setSubject(mail.getTitle());

        return simpleMailMessage;
    }
}
