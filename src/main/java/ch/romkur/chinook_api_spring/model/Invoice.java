package ch.romkur.chinook_api_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data @Entity @Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoiceid")
    private Integer id;

    @ManyToOne @JoinColumn(name="customerid")
    private Customer customer;

    @Column(name = "invoicedate") private LocalDate invoicedate;
    @Column(name = "billingaddress") private String billingaddress;
    @Column(name = "billingcity") private String billingcity;
    @Column(name = "billingstate") private String billingstate;
    @Column(name = "billingcountry") private String billingcountry;
    @Column(name = "billingpostalcode") private String billingpostalcode;
    @Column(name = "total") private BigDecimal total;
}
