package ch.romkur.chinook_api_spring.model;

import ch.romkur.chinook_api_spring.dto.PlaylistHeaderDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity @Table(name = "playlist") @Data
public class Playlist {

    public static List<PlaylistHeaderDto> toDtoList(List<Playlist> inp) {
        return inp.stream()
                .map(Playlist::toDto)
                .toList();
    }

    @Id @Column(name = "playlistid") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToMany
    @JoinTable(name = "playlisttrack",
        joinColumns = @JoinColumn(name = "playlistid"),
            inverseJoinColumns = @JoinColumn(name = "trackid")
    )
    private List<Track> tracks;

    public PlaylistHeaderDto toDto() {return new PlaylistHeaderDto(this.id, this.name);}
}
