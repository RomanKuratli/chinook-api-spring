package ch.romkur.chinook_api_spring.dto;

public record EmployeeDto(
        Integer id,
        String firstName,
        String lastName,
        String title,
        String reportsTo
) {
}
