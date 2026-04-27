package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.model.Artist;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "album")
@Data
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "albumid")
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "artistid")
    private Artist artist;
}