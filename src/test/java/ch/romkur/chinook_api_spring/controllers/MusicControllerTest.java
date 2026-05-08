package ch.romkur.chinook_api_spring.controllers;
import ch.romkur.chinook_api_spring.dto.AvgTrackPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

class MusicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSortedGenresByPrice() throws Exception {
        String json = mockMvc.perform(
                get("/api/genres/stats/avg_track_price3")
        ).andExpect(status().isOk())
        .andReturn().getResponse().getContentAsString();

        // Nutze den Jackson ObjectMapper (ist bei Spring Boot dabei),
        // um das JSON in eine Liste von Maps oder deine Records zu wandeln
        ObjectMapper mapper = new ObjectMapper();
        List<AvgTrackPrice> list = mapper.readValue(json, new TypeReference<List<AvgTrackPrice>>() {});
        for (int i = 0; i < list.size() - 1; i ++) {
            var avg1 = list.get(i).avgTrackPrice();
            var avg2 = list.get(i + 1).avgTrackPrice();
            assertTrue(avg1.compareTo(avg2) >= 0);
        }
    }
}