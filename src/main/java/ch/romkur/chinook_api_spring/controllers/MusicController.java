package ch.romkur.chinook_api_spring.controllers;

import ch.romkur.chinook_api_spring.model.Album;
import ch.romkur.chinook_api_spring.model.Artist;
import ch.romkur.chinook_api_spring.model.Track;
import ch.romkur.chinook_api_spring.repos.AlbumRepo;
import ch.romkur.chinook_api_spring.repos.ArtistRepo;
import ch.romkur.chinook_api_spring.repos.TrackRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
class MusicController {
    private final ArtistRepo artistRepo;
    private final AlbumRepo albumRepo;
    private final TrackRepo trackRepo;

    public MusicController(ArtistRepo artistRepo, AlbumRepo albumRepo, TrackRepo trackRepo) {
        this.artistRepo = artistRepo;
        this.albumRepo = albumRepo;
        this.trackRepo = trackRepo;
    }

    @GetMapping("/artists")
    public List<Artist> getAllArtists(
            @RequestParam(value="search", required = false) String search
    ) {
        if (search != null && !search.trim().isEmpty()) {
            return artistRepo.findByNameContainingIgnoreCase(search);
        }
        return artistRepo.findAll();
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable Integer id) {
        return ResponseEntity.of(artistRepo.findById(id));
    }

    @GetMapping("/artists/{id}/albums")
    public List<Album> getAlbumsByArtist(@PathVariable Integer id) {
        return albumRepo.findByArtistId(id);
    }

    @GetMapping("/albums/{id}/tracks")
    public List<Track> getTracksByAlbum(@PathVariable Integer id) {
        return trackRepo.findByAlbumId(id);
    }
}
