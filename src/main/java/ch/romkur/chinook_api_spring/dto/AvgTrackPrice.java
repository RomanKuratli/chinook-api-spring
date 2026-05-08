package ch.romkur.chinook_api_spring.dto;

import java.math.BigDecimal;

public record AvgTrackPrice(String genre, BigDecimal avgTrackPrice) {
    public AvgTrackPrice(String genre, Double avgTrackPrice) {
        this(genre, BigDecimal.valueOf(avgTrackPrice));
    }
}
