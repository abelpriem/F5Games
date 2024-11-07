package org.f5games.aiden_game.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.repository.CharacterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {
    @Mock
    CharacterRepository characterRepository;
    @InjectMocks
    CharacterService characterService;
    @Mock
    Character mockCharacter1;
    @Mock
    Character mockCharacter2;
    @Mock
    Character mockCharacter3;

    @BeforeEach
    void setUp() {

        mockCharacter1 = Character.builder().id(1L).name("Hulk").strength(20).health(200).build();
        mockCharacter2 = Character.builder().id(2L).name("Superman").strength(40).health(400).build();
        mockCharacter3 = Character.builder().id(3L).name("Spiderman").strength(40).health(400).build();
    }

    @Test
    void testGetAll() {
        List<Character> mockCharacters = new ArrayList<>();

        mockCharacters.add(mockCharacter1);
        mockCharacters.add(mockCharacter2);
        
        when(characterRepository.findAll()).thenReturn(mockCharacters);

        List<Character> result = characterService.getAll();
        assertTrue(result.equals(mockCharacters));
    }

    @Test
    void testUpdateOneIdExists() {
        
        Long id=2L;
        
        mockCharacter2.setId(id); 
        mockCharacter2.setName("Batman");
        mockCharacter2.setStrength(15);
        mockCharacter2.setHealth(150);

        when(characterRepository.existsById(id)).thenReturn(true);
        when(characterRepository.save(mockCharacter2)).thenReturn(mockCharacter2);
        Character result = characterService.updateOne(mockCharacter2);
        assertNotNull(result);
        assertEquals(mockCharacter2,result);


    }

    @Test
    void testUpdateOneIdDoesNotExist() {

        Long id=4L;

        mockCharacter2.setId(id); 
        mockCharacter2.setName("Batman");
        mockCharacter2.setStrength(15);
        mockCharacter2.setHealth(150);


        when(characterRepository.existsById(id)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> {
            characterService.updateOne(mockCharacter2);});
        
    }

    @Test
    void testGetCharacterById() {
        Long id = 3L;

        when(characterRepository.findById(id)).thenReturn(Optional.of(mockCharacter3));
        Character result = characterService.getCharacterById(id);
        assertEquals(mockCharacter3,result);
    }

    @Test
    void testGetCharacterByIdNotFound() {
    Long id = 3L;

    when(characterRepository.findById(id)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> {
        characterService.getCharacterById(id);
    });
}

}
