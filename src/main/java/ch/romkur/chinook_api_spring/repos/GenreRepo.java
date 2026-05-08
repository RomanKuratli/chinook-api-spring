package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.dto.AvgTrackPrice;
import ch.romkur.chinook_api_spring.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Integer> {
    @Query("""
           SELECT new ch.romkur.chinook_api_spring.dto.AvgTrackPrice(g.name, AVG(t.unitPrice))
           FROM Genre g
           JOIN g.tracks t
           GROUP BY g.name
           """)
    List<AvgTrackPrice> getAvgTrackPricePerGenre();
}
