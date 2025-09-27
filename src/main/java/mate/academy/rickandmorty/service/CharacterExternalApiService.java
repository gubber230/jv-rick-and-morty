package mate.academy.rickandmorty.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.external.CharacterResponseDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.exception.ExternalApiException;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacterExternalApiService {
    private static final String BASE_URL = "https://rickandmortyapi.com/api/character";
    private final ObjectMapper objectMapper;
    private final CharacterMapper characterMapper;
    private final CharacterService characterService;

    public void fetchCharacters() {
        List<CharacterDto> characters = new ArrayList<>();
        String nextUrl = BASE_URL;

        HttpClient client = HttpClient.newHttpClient();
        while (nextUrl != null) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(nextUrl))
                    .build();
            try {
                HttpResponse<String> response = client.send(
                        request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    throw new IOException("API returned non-successful status code: "
                            + response.statusCode());
                }
                CharacterResponseDto characterResponseDto = objectMapper.readValue(
                        response.body(), CharacterResponseDto.class);

                characters.addAll(characterMapper.externalToDtoList(
                        characterResponseDto.results()));
                nextUrl = characterResponseDto.info().next();
            } catch (IOException | InterruptedException e) {
                throw new ExternalApiException(
                        "An error occurred while trying to get a response from the server.", e);
            }
        }
        characterService.saveAll(characters);
    }

}
