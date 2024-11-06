package org.f5games.aiden_game.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.repository.BackpackRepository;
import org.f5games.aiden_game.repository.GameObjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BackpackServiceTest {
    @Mock
    BackpackRepository backpackRepository;
    @Mock
    GameObjectRepository gameObjectRepository;
    @InjectMocks
    BackpackService backpackService;

    @Test
    public void testGetAll(){
        Backpack b1 = new Backpack(1L,GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build());
        Backpack b2 = new Backpack(2L,GameObject.builder().id(2L).name("mockObject2").description("mockDescription2").special(true).build());
        List<Backpack>expected = List.of(b1,b2);
        when(backpackRepository.findAll()).thenReturn(expected);
        List<Backpack>result = backpackService.getAll();
        assertTrue(result.equals(expected));
    }

    @Test
    public void testInsertOne_WhenGameObjectIdExists(){
        Long objectId = 1l;
        GameObject gameObject = GameObject.builder().id(objectId).name("mockObject").description("mockDescription").special(true).build();
        Backpack newBackpack = new Backpack(null,gameObject);
        Backpack createdBackpack = new Backpack(1L,gameObject);

        when(backpackRepository.save(newBackpack)).thenReturn(createdBackpack);
        when(gameObjectRepository.findById(objectId)).thenReturn(Optional.of(gameObject));
        Backpack result = backpackService.insertOne(objectId);
        assertTrue(result.equals(createdBackpack));
    }

    @Test
    public void testInsertOne_WhenGameObjectIdDoesNotExist(){
        Long objectId = 1l;
        when(gameObjectRepository.findById(objectId)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,() -> {backpackService.insertOne(objectId);});
    }

    @Test
    public void testInsertOne_WhenGameObjectIdIsNull(){
        Long objectId = null;
        assertThrows(IllegalArgumentException.class,() -> {backpackService.insertOne(objectId);});
    }

    @Test
    public void testInsertOne_WhenBackpackIsFull(){
        Long objectId = 1L;
        when(backpackRepository.count()).thenReturn(3L);
        assertThrows(IllegalArgumentException.class,() -> {backpackService.insertOne(objectId);});
    }

    @Test
    public void testDeleteOne_WhenBackpackIdExists(){
        Long id = 1L;
        Backpack b1 = new Backpack(1L,GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build());
        when(backpackRepository.findById(id)).thenReturn(Optional.of(b1));
        backpackService.deleteOne(id);
        verify(backpackRepository, times(1)).delete(b1);
    }

    @Test
    public void testDeleteOne_WhenBackpackIdDoesNotExist(){
        Long id = 1L;
        when(backpackRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class,() -> {backpackService.deleteOne(id);});
    }

    @Test
    public void testDeleteOne_WhenBackpackIdIsNull(){
        assertThrows(IllegalArgumentException.class,() -> {backpackService.deleteOne(null);});
    }

    @Test
    public void testUpdateOne_WhenBackpackIdIsNull(){
        Long id = null;
        Long objectId = 1L;
        assertThrows(IllegalArgumentException.class,() -> {backpackService.updateOne(id, objectId);});
    }

    @Test
    public void testUpdateOne_WhenGameObjectIdIsNull(){
        Long id = 1L;
        Long objectId = null;
        assertThrows(IllegalArgumentException.class,() -> {backpackService.updateOne(id, objectId);});
    }

    @Test
    public void testUpdateOne_WhenGameObjectIdDoesNotExist(){
        Long id = 1L;
        Long objectId = 2L;
        Backpack b1 = new Backpack(1L,GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build());
        when(gameObjectRepository.findById(objectId)).thenReturn(Optional.empty());
        when(backpackRepository.findById(id)).thenReturn(Optional.of(b1));
        assertThrows(IllegalArgumentException.class, () -> {backpackService.updateOne(id, objectId);});
    }

    @Test
    public void testUpdateOne_WhenBackpackIdDoesNotExist(){
        Long id = 1L;
        Long objectId = 1L;
        when(backpackRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> {backpackService.updateOne(id, objectId);});
    }

    @Test
    public void testUpdateOne_WhenBothIdsExist_UpdatesBackpackSuccessfully(){
        Long id = 1L;
        Long initialObjectId = 1L;
        Long updatedObjectId = 2L;
        GameObject initialObject = GameObject.builder().id(initialObjectId).name("mockObject").description("mockDescription").special(true).build();
        GameObject updatedObject = GameObject.builder().id(updatedObjectId).name("mockObject2").description("mockDescription2").special(true).build();
        Backpack initialBackpack = Backpack.builder().gameObject(initialObject).id(id).build();
        Backpack updatedBackpack = Backpack.builder().gameObject(updatedObject).id(id).build();
        when(backpackRepository.findById(id)).thenReturn(Optional.of(initialBackpack));
        when(gameObjectRepository.findById(updatedObjectId)).thenReturn(Optional.of(updatedObject));
        when(backpackRepository.save(updatedBackpack)).thenReturn(updatedBackpack);
        Backpack result = backpackService.updateOne(id,updatedObjectId);
        assertEquals(updatedBackpack,result);
        verify(backpackRepository,times(1)).findById(id);
        verify(gameObjectRepository,times(1)).findById(updatedObjectId);
        verify(backpackRepository,times(1)).save(updatedBackpack);
    }
}
