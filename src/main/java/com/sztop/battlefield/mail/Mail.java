package com.sztop.battlefield.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Mail {

    private String recipent;
    private String cc;

    private String title;
    private String content;
    private String fromAddr;
}
