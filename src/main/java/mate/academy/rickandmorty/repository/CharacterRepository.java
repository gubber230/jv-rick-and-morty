package mate.academy.rickandmorty.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.rickandmorty.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CharacterRepository extends JpaRepository<Character, Long> {
    List<Character> findByNameContainingIgnoreCase(String name);

    Optional<Character> findByExternalId(Long externalId);

    @Query("SELECT MAX(c.externalId) FROM Character c")
    Optional<Long> findMaxExternalId();
}
