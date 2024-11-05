package org.f5games.aiden_game.models;

import java.util.List;
import org.f5games.aiden_game.controllers.BackpackController;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @Autowired
    private BackpackController backpackController;

    // Método que lista la mochila
    public List<Backpack> viewBackpack() {
        // Aquí estamos llamando al controlador, lo cual no es lo ideal
        return backpackController.retrieveObjects().getBody();
    }

    // Método que añade un objeto
    public void addBackpack(Long objectId) {
        // Aquí estamos llamando al controlador, lo cual no es lo ideal
        backpackController.addObject(objectId);
    }

    // Método que modifica un objeto
    public void updateBackpack(Long id, Long objectId) {
        // Aquí estamos llamando al controlador, lo cual no es lo ideal
        backpackController.updateObject(id, objectId);
    }


    // Método que modifica un objeto
    public void deleteBackpack(Long id) {
        // Aquí estamos llamando al controlador, lo cual no es lo ideal
        backpackController.deleteObject(id);
    }

}
