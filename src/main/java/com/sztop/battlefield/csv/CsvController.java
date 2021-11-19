package com.sztop.battlefield.csv;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("csv")
@Profile("csv")
public class CsvController {

    private final CsvExporter csvExporter;

    @GetMapping("download")
    public void exportCsv(HttpServletResponse response) throws IOException {

        csvExporter.exportInMemory(response);

    }
}
