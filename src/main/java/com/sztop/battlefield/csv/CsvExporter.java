package com.sztop.battlefield.csv;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@Component
@Slf4j
public class CsvExporter {

    private final EntityCsvCreator entityCsvCreator;

    private static final String CONTENT_TYPE = "text/csv";
    private static final String DISPOSITION = "attachment; filename=";
    private static final String ARRAY_SEPARATOR = ",";
    private static final char COLUMN_SEPARATOR = ',';

    public void exportInMemory(HttpServletResponse response) throws IOException {
        export(response, EntityCsvCreator.inMemoryRecords, EntityCsvCreator.EntityCsvRecord.class, entityCsvCreator.csvFileName());
    }

    public void export(HttpServletResponse response, List<? extends CsvRecord<?>> records,
                       Class<? extends CsvRecord<?>> exportableClass, String fileName) throws IOException {

        Instant start = Instant.now();
        log.info("Exporting in-memory records storage started | timestamp:{}", start);

        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(exportableClass)
                .withColumnSeparator(COLUMN_SEPARATOR)
                .withUseHeader(true)
                .withArrayElementSeparator(ARRAY_SEPARATOR);

        AtomicInteger number = new AtomicInteger(1);
        records.forEach(csvRecord -> csvRecord.setNumber(number.getAndIncrement()));

        ObjectWriter objectWriter = mapper.writer(schema);
        response.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, DISPOSITION + fileName);

        try(OutputStreamWriter outputStreamWriter = new OutputStreamWriter(response.getOutputStream(), StandardCharsets.UTF_8)){

            objectWriter.writeValue(outputStreamWriter, records);
        }

        Instant end = Instant.now();
        log.info("Exporting in-memory records storage ended | timestamp:{} - duration {}s", end, Duration.between(start, end).toSeconds());
    }
}
