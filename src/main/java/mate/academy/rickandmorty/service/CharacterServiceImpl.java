package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.mapper.CharacterMapper;
import mate.academy.rickandmorty.model.Character;
import mate.academy.rickandmorty.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void saveAll(List<CharacterDto> dtos) {
        List<Character> characters = dtos.stream()
                .map(characterDto -> {
                    Character newCharacter = repository
                            .findByExternalId(characterDto.externalId())
                            .orElse(mapper.internalToModel(characterDto));
                    mapper.updateEntityFromDto(characterDto, newCharacter);
                    return newCharacter;
                })
                .toList();
        repository.saveAll(characters);
    }

    @Override
    public Character getById(Long id) {
        return repository.findByExternalId(id).orElseThrow(
                () -> new EntityNotFoundException(
                        "Could not find Character with id: " + id)
        );
    }

    @Override
    public List<Character> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Character getRandomCharacter() {
        Long maxId = repository.findMaxExternalId()
                .orElseThrow(() ->
                        new EntityNotFoundException("Could not find max characters count"));
        long randomId = new Random().nextLong(1, maxId + 1);
        return repository.findById(randomId)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Error occurred when trying to find Character with id: "
                                        + randomId)
                );
    }
}
