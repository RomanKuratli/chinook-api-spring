package ch.romkur.chinook_api_spring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "track")
@Data
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trackid")
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "albumid")
    private Album album;

    @ManyToOne
    @JoinColumn(name = "mediatypeid")
    private MediaType mediaType;

    @ManyToOne
    @JoinColumn(name = "genreid")
    private Genre genre;

    private String composer;
    private Integer milliseconds;
    private Integer bytes;

    @Column(name = "unitprice")
    private BigDecimal unitPrice;
}
