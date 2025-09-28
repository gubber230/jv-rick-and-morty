package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;

public interface CharacterService {
    void saveAll(List<CharacterDto> dtos);

    CharacterDto getById(Long id);

    List<CharacterDto> findByName(String name);

    CharacterDto getRandomCharacter();
}
