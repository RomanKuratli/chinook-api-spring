package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepo extends JpaRepository<Artist, Integer> {
    // Spring baut automatisch eine Suche nach dem Namen:
    List<Artist> findByNameContainingIgnoreCase(String name);
}