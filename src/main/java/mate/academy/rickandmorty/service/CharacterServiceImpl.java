package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {
    private final CharacterMapper mapper;
    private final CharacterRepository repository;

    @Override
    public void save(CharacterDto characterDto) {
        Character character = mapper.internalToModel(characterDto);
        repository.save(character);
    }

    @Override
    public void saveAll(List<CharacterDto> dtos) {
        List<Character> characters = dtos.stream()
                .map(mapper::internalToModel)
                .toList();
        repository.saveAll(characters);
    }

    @Override
    public Character getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Could not find Character with id: " + id)
        );
    }

    @Override
    public List<Character> findByName(String name) {
        return repository.findByName(name);
    }
}
