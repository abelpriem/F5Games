package org.f5games.aiden_game;

import static org.junit.jupiter.api.Assertions.*;

import org.f5games.aiden_game.controllers.BackpackController;
import org.f5games.aiden_game.controllers.CharacterController;
import org.f5games.aiden_game.controllers.GameObjectController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameMenuTest {
    
    private GameMenu gameMenu;
    
    private CharacterController characterController;
    private BackpackController backpackController;
    private GameObjectController objectController;

    @BeforeEach
    public void init() {
        this.gameMenu = new GameMenu(characterController, backpackController, objectController);
    }

    @Test
    public void testStart() {
        
    }
    
    @Test
    void testcalculateTimeBonus(){
        long durationInSeconds = 4;

        int result = gameMenu.calculateTimeBonus(durationInSeconds);
        assertEquals(50,result);

    }
    
    
}

