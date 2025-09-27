package mate.academy.rickandmorty.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.service.CharacterService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/characters")
@Validated
public class CharacterController {
    private final CharacterService characterService;

    @Operation(description = "The request randomly generates a wiki "
            + "about one character in the universe the animated series Rick & Morty")
    @GetMapping("/random")
    public Character getRandomCharacter() {
        return characterService.getRandomCharacter();
    }

    @Operation(description = "The request takes a string as an argument,"
            + " and returns a list of all characters whose name contains the search string.")
    @GetMapping
    public List<Character> getAllCharactersByName(
            @Parameter(example = "?name=Rick+Sanchez")
            @RequestParam("name")
            @NotBlank
            String name) {
        return characterService.findByName(name);
    }
}
