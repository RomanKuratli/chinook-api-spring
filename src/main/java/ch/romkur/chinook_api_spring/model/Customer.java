package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.dto.CustomerDto;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "customer") @Data
public class Customer {
    @Id @Column(name = "customerid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstname;
    private String lastname;
    private String company;
    private String address;
    private String city;
    private String state;
    private String country;
    private String postalcode;
    private String phone;
    private String fax;
    private String email;

    public CustomerDto toDto() {
        return new CustomerDto(
                id, firstname, lastname, company, city
        );
    }
}
