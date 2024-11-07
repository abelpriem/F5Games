package org.f5games.aiden_game.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "entity_class")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Aiden.class, name = "AIDEN"),
        @JsonSubTypes.Type(value = Skeleton.class, name = "SKELETON"),
        @JsonSubTypes.Type(value = Ghost.class, name = "GHOST"),
        @JsonSubTypes.Type(value = Vampire.class, name = "VAMPIRE"),
        @JsonSubTypes.Type(value = Mortis.class, name = "MORTIS")
})
@Entity
@DiscriminatorColumn(name = "entity_class", discriminatorType = DiscriminatorType.STRING)
@Table(name = "characters")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer strength;
    @Column(nullable = false)
    private Integer health;
}
