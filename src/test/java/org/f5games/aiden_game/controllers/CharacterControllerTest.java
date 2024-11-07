package org.f5games.aiden_game.controllers;

import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import org.f5games.aiden_game.GameMenu;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.services.CharacterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;



@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    private GameMenu gameMenu;

    @MockBean
    CharacterService characterService;

    private Character mockcharacter;

    @BeforeEach
    public void setUp() {

        mockcharacter = Character.builder().id(1L).name("Hulk").strength(20).health(200).build();
    }

      
    @Test
    @DisplayName("Should return a list of characteres")
    void testGetCharacter() throws Exception {
        List<Character> mockCharacters = new ArrayList<>();
        
        mockCharacters.add(mockcharacter);
        
        when(characterService.getAll()).thenReturn(mockCharacters);

        mockMvc.perform(get("/character"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("Hulk"))
            .andExpect(jsonPath("$[0].strength").value(20))
            .andExpect(jsonPath("$[0].health").value(200));
    }

    @Test
    @DisplayName("Should return a character")
    void testUpdateCharacter() throws Exception {

        when(characterService.updateOne(mockcharacter)).thenReturn(mockcharacter);
         
         mockMvc.perform(put("/character")
         .contentType(MediaType.APPLICATION_JSON)
         .content(asJsonString(mockcharacter)))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$.id").value(1L))
         .andExpect(jsonPath("$.name").value("Hulk"))
         .andExpect(jsonPath("$.strength").value(20))
         .andExpect(jsonPath("$.health").value(200));
    }
        
    
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}



