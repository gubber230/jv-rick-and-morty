package mate.academy.rickandmorty.dto.internal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CharacterDto(
        Long externalId,
        String name,
        String status,
        String gender) {
}
