package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.dto.TrackDto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data @Entity @Table(name = "track")
public class Track {
    public static List<TrackDto> toDtoList(List<Track> inp) {
        return inp.stream()
                .map(Track::toDto)
                .toList();
    }

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

    @Column(name = "composer")private String composer;
    @Column(name = "milliseconds")private Integer milliseconds;
    @Column(name = "bytes")private Integer bytes;

    @Column(name = "unitprice")
    private BigDecimal unitPrice;

    public TrackDto toDto() {
        return new TrackDto(
                id,
                name,
                mediaType.getName(),
                genre.getName(),
                composer,
                milliseconds,
                bytes,
                unitPrice
        );
    }
}
