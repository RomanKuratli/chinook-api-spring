package ch.romkur.chinook_api_spring.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "artist")
@Data // Lombok: Generiert Getter, Setter, toString
@NoArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artistid")
    private Integer id;

    private String name;
}