package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity  @Table(name ="employee") @Data
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name ="employeeid") private Integer id;

    @OneToOne @JoinColumn(name = "reportsto") private Employee reportsTo;

    @Column(name = "birthdate") private LocalDate birthDate;
    @Column(name = "hiredate") private LocalDate hireDate;
    @Column(name = "firstname") private String firstName;
    @Column(name = "lastname") private String lastName;
    @Column(name = "title") private String title;
    @Column(name = "address") private String address;
    @Column(name = "city") private String city;
    @Column(name = "state") private String state;
    @Column(name = "country") private String country;
    @Column(name = "postalcode") private String postalcode;
    @Column(name = "phone") private String phone;
    @Column(name = "fax") private String fax;
    @Column(name = "email") private String email;

    public EmployeeDto toDto() {
        var bossName = reportsTo == null ? "-" : "%s %s".formatted(reportsTo.firstName, reportsTo.lastName);
        return new EmployeeDto(
            id, firstName, lastName, title, bossName
        );
    }
}
