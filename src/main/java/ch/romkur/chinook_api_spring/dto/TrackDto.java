package ch.romkur.chinook_api_spring.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;

public record TrackDto(
        Integer id,
        String name,
        String mediaType,
        String genre,
        String composer,
        Integer milliseconds,
        Integer bytes,
        @Column(precision = 10, scale = 2)
        BigDecimal unitprice
        ) {
}
