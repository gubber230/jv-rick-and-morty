package mate.academy.rickandmorty.service;

import java.util.List;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;

public interface CharacterService {
    void save(CharacterDto characterDto);

    void saveAll(List<CharacterDto> dtos);

    Character getById(Long id);

    List<Character> findByName(String name);
}
