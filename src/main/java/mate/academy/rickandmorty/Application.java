package mate.academy.rickandmorty;

import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.service.CharacterExternalApiService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class Application implements CommandLineRunner {
    private final CharacterExternalApiService externalApiService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        externalApiService.fetchCharacters();
    }
}
