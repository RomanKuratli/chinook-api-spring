package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.model.Artist;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "genre")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genreid")
    private Integer id;

    private String name;
}