package mate.academy.rickandmorty.mapper;

import java.util.List;
import mate.academy.rickandmorty.config.MapperConfig;
import mate.academy.rickandmorty.dto.external.CharacterResultsDto;
import mate.academy.rickandmorty.dto.internal.CharacterDto;
import mate.academy.rickandmorty.model.Character;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CharacterMapper {
    CharacterDto toDto(Character character);

    @Mapping(target = "id", ignore = true)
    Character internalToModel(CharacterDto characterDto);

    @Mapping(target = "externalId", source = "id")
    CharacterDto externalToDto(CharacterResultsDto characterResultsDto);

    List<CharacterDto> externalToDtoList(List<CharacterResultsDto> externalDtos);

    void updateEntityFromDto(CharacterDto characterDto, @MappingTarget Character Character);
}
