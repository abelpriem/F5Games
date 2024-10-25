package org.f5games.aiden_game.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "backpacks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Backpack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "game_object_id")
    private GameObject gameObject;
    
}
