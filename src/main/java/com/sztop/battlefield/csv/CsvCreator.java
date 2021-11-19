package com.sztop.battlefield.csv;

abstract class CsvCreator {

    static final String DATE_FORMAT = "dd-MM-yyyy-hhmmss";
    static final String CSV_SUFIX = ".csv";

    abstract String csvFileName();

}
