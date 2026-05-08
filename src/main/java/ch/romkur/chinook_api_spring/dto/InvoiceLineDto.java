package ch.romkur.chinook_api_spring.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class InvoiceLineDto {
    private Integer id;
    private String trackName;
    private BigDecimal unitPrice;
    private Integer quantity;
}
