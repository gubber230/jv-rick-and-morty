package mate.academy.rickandmorty.service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
    public CharacterDto getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Could not find Character with id: " + id)
        );
    }

    @Override
    public List<CharacterDto> findByName(String name) {
        return repository.findByNameContainingIgnoreCase(name).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CharacterDto getRandomCharacter() {
        return mapper.toDto(repository.findRandomCharacter());
    }
}
