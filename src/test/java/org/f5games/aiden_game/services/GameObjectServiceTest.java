package org.f5games.aiden_game.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repository.GameObjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GameObjectServiceTest {
    @Mock
    GameObjectRepository gameObjectRepository;
    @InjectMocks
    GameObjectService gameObjectService;
    
    @Test
    public void testGetAll(){
        GameObject o1 = GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build();
        GameObject o2 = GameObject.builder().id(2L).name("mockObject2").description("mockDescription2").special(true).build();
        List<GameObject>expected = List.of(o1,o2);
        when(gameObjectRepository.findAll()).thenReturn(expected);
        List<GameObject>result = gameObjectService.getAll();
        assertTrue(result.equals(expected));
    }
}
