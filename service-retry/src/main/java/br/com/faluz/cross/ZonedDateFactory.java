package br.com.faluz.cross;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ZonedDateFactory {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String formatAndParseDate(LocalDate date) {
        return date.format(DATE_FORMATTER);
    }

    public LocalDate parseFormattedDate(String date) {
        return LocalDate.parse(date, DATE_FORMATTER);
    }
}
