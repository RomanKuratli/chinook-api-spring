package ch.romkur.chinook_api_spring.controllers;

import ch.romkur.chinook_api_spring.dto.AvgTrackPrice;
import ch.romkur.chinook_api_spring.dto.PlaylistHeaderDto;
import ch.romkur.chinook_api_spring.dto.TrackDto;
import ch.romkur.chinook_api_spring.model.*;
import ch.romkur.chinook_api_spring.repos.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
class MusicController {
    private final ArtistRepo artistRepo;
    private final AlbumRepo albumRepo;
    private final TrackRepo trackRepo;
    private final GenreRepo genreRepo;
    private final PlaylistRepo plRepo;

    public MusicController(ArtistRepo artistRepo, AlbumRepo albumRepo, TrackRepo trackRepo,
                           GenreRepo genreRepo, PlaylistRepo plRepo) {
        this.artistRepo = artistRepo;
        this.albumRepo = albumRepo;
        this.trackRepo = trackRepo;
        this.genreRepo = genreRepo;
        this.plRepo = plRepo;
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
    public List<TrackDto> getTracksByAlbum(@PathVariable Integer id) {
        return Track.toDtoList(trackRepo.findByAlbumId(id));
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }

    @GetMapping("genres/{id}/tracks")
    public List<TrackDto> getTrackByAlbum(@PathVariable Integer id) {
        return Track.toDtoList(trackRepo.findByGenreId(id));
    }

    @GetMapping("/genres/stats/avg_track_price")
    public List<AvgTrackPrice> avgTrackPricePerGenre() {
        return genreRepo.findAll().stream()
            .map(genre -> {
               var tracks = trackRepo.findByGenreId(genre.getId());
               var avgPrice = tracks.isEmpty() ? BigDecimal.ZERO :
                   tracks.stream()
                        .map(Track::getUnitPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
                        .divide(BigDecimal.valueOf(tracks.size()), RoundingMode.HALF_UP);

               return new AvgTrackPrice(genre.getName(), avgPrice);
            })
            .sorted(Comparator.comparing(AvgTrackPrice::avgTrackPrice).reversed())
            .toList();
    }

    @GetMapping("/genres/stats/avg_track_price2")
    public List<AvgTrackPrice> avgTrackPricePerGenre2() {
        ArrayList<AvgTrackPrice> result = new ArrayList<>();
        for (Genre genre : genreRepo.findAll()) {
            var tracks = trackRepo.findByGenreId(genre.getId());
            var totalPrice = tracks.stream()
                    .map(Track::getUnitPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            var avgPrice = totalPrice.divide(new BigDecimal(tracks.size()), RoundingMode.HALF_UP);
            result.add(new AvgTrackPrice(genre.getName(), avgPrice));
        }
        result.sort((a, b) -> {
            var diff = a.avgTrackPrice().subtract(b.avgTrackPrice());
            if (diff.equals(BigDecimal.ZERO)) return 0;
            if (diff.compareTo(BigDecimal.ZERO) > 0) return -1;
            return 1;
        });
        return result;
    }

    @GetMapping("/genres/stats/avg_track_price3")
    public List<AvgTrackPrice> avgTrackPricePerGenre3() {
        return genreRepo.getAvgTrackPricePerGenre().stream()
                .sorted(Comparator.comparing(AvgTrackPrice::avgTrackPrice).reversed())
                .toList();
    }

    @GetMapping("/playlists")
    public List<PlaylistHeaderDto> getAllPlaylists(Integer playlistId) {
        return Playlist.toDtoList(plRepo.findAll());
    }

    @GetMapping("/playlists/{id}/tracks")
    public List<TrackDto> getTracksByPlaylist(@PathVariable Integer id) {
        return plRepo.findById(id)
                .map(Playlist::getTracks)
                .map(Track::toDtoList)
                .orElse(new ArrayList<>());
    }
}
