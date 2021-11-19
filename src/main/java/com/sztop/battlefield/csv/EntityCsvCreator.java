package com.sztop.battlefield.csv;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class EntityCsvCreator extends CsvCreator{

    private static final String DOCUMENT_CSV_PREFIX = "status_";
    public static final List<CsvRecord<EntityCsvRecord>> inMemoryRecords = new ArrayList<>();

    static {
        inMemoryRecords.add(EntityCsvRecord.builder()
                .name("test1")
                .id(1L)
                .name("Dwight")
                .surname("Shrut")
                .build());
        inMemoryRecords.add(EntityCsvRecord.builder()
                .name("test2")
                .id(2L)
                .name("Jim")
                .surname("Halpert")
                .build());
        inMemoryRecords.add(EntityCsvRecord.builder()
                .name("test3")
                .id(3L)
                .name("Pam")
                .surname("Beasley")
                .build());
    }


    @Override
    String csvFileName() {
        return DOCUMENT_CSV_PREFIX + LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)) + CSV_SUFIX;
    }

    //HERE INMEMORYRECORDS MANIPULATION - TO DTO? OR STH LIKE THIS

    @Getter
    @Setter
    @Builder
    public static class EntityCsvRecord implements CsvRecord<EntityCsvRecord>{

        @JsonProperty("Id")
        Long id;

        @JsonProperty("Number")
        int number;

        @JsonProperty("Name")
        String name;

        @JsonProperty("Surname")
        String surname;

        @Override
        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public int compareTo(EntityCsvRecord o) {
            return this.getId().compareTo(o.getId());
        }
    }
}
