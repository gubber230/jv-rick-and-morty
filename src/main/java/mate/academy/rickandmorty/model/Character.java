package mate.academy.rickandmorty.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "characters")
@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @NotNull
    private Long externalId;
    @Column(nullable = false)
    @NotBlank
    private String name;
    @Column(nullable = false)
    @NotBlank
    private String status;
    @Column(nullable = false)
    @NotBlank
    private String gender;
}
