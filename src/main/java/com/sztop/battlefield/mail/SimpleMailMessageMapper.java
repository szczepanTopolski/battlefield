package com.sztop.battlefield.mail;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.mail.SimpleMailMessage;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = MapperUtils.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface SimpleMailMessageMapper {

    @Mapping(target = "subject", source = "title")
    @Mapping(target = "text", source = "context")
    @Mapping(target = "from", source = "fromAddr")
    @Mapping(target = "cc", source = "cc", qualifiedByName = "splitString")
    @Mapping(target = "to", source = "recipent", qualifiedByName = "splitString")
    SimpleMailMessage map(Mail mail);
}
