package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackRepo extends JpaRepository<Track, Integer> {
    List<Track> findByAlbumId(Integer albumId);
}
