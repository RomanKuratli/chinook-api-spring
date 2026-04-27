package ch.romkur.chinook_api_spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "mediatype")
@Data
public class MediaType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mediatypeid")
    private Integer id;

    private String name;
}
