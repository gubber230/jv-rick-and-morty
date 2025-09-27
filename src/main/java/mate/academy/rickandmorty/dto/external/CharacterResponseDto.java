package mate.academy.rickandmorty.dto.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CharacterResponseDto(
        CharacterInfoDto info,
        List<CharacterResultsDto> results) {
}
