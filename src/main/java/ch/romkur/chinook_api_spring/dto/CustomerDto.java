package ch.romkur.chinook_api_spring.dto;

public record CustomerDto(
    Integer id,
    String firstName,
    String lastName,
    String company,
    String city
) {
}
