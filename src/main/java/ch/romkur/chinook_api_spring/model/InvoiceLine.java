package ch.romkur.chinook_api_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import ch.romkur.chinook_api_spring.dto.InvoiceLineDto;

import java.math.BigDecimal;

@Data @Entity @Table(name = "invoiceline")
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoicelineid")
    private Integer id;

    @ManyToOne @JoinColumn(name="invoiceid")
    private Invoice invoice;

    @ManyToOne @JoinColumn(name="trackid")
    private Track track;

    @Column(name = "unitprice") private BigDecimal unitprice;
    @Column(name = "quantity") private Integer quantity;

    public InvoiceLineDto toDto() {
        InvoiceLineDto dto = new InvoiceLineDto();
        dto.setId(this.id);
        
        if (this.track != null) {
            dto.setTrackName(this.track.getName());
        }
        
        return dto;
    }
}
