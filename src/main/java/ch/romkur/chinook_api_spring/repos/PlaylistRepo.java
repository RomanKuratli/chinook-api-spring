package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer> {
}
