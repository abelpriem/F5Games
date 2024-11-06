package org.f5games.aiden_game.controllers;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.f5games.aiden_game.GameMenu;
import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.Character;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.CharacterService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    CharacterService characterService;

    @InjectMocks
    private CharacterController characterController;

    @MockBean
    private GameMenu gameMenu;

    @Autowired
    ObjectMapper mapper;
    
    @Test
    @DisplayName("Should return a list of characteres")




    void testGetCharacter() throws Exception {
        List<Character> mockCharacters = new ArrayList<>();
        
        mockCharacters.add(Character.builder().id(1L).name("Aiden").strength(10).health(100).build());
        
        when(characterService.getAll()).thenReturn(mockCharacters);

        mockMvc.perform(get("/character"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(1))
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].name").value("Aiden"))
            .andExpect(jsonPath("$[0].strength").value(10))
            .andExpect(jsonPath("$[0].health").value(100));
    }

    // @Test
    // public void testUpdateCharacter() throws Exception {
    // Long id = 1L;
    // String name = "Hulk";
    // int strength = 20;
    // int health = 200;

    // // Crea el objeto Character mockeado que será devuelto por el servicio
    // Character mockCharacter = Character.builder()
    //     .id(id)
    //     .name(name)
    //     .strength(strength)
    //     .health(health)
    //     .build();

    // // Simula que el servicio devuelve el personaje actualizado
    // when(characterService.updateOne(mockCharacter)).thenReturn(mockCharacter);

    // // Realiza la solicitud PUT incluyendo el cuerpo con los datos del personaje
    // mockMvc.perform(put("/character")
    //     .contentType(MediaType.APPLICATION_JSON)  // Especifica el tipo de contenido
    //     .content("{\"id\":1, \"name\":\"Hulk\", \"strength\":20, \"health\":200}")) // Cuerpo de la solicitud
    //     .andExpect(status().isOk())  // Verifica que la respuesta tenga el estado 200 OK
    //     .andExpect(jsonPath("$.id").value(1L))  // Verifica que el id sea 1
    //     .andExpect(jsonPath("$.name").value("Hulk"))  // Verifica que el nombre sea Hulk
    //     .andExpect(jsonPath("$.strength").value(20))  // Verifica que la fuerza sea 20
    //     .andExpect(jsonPath("$.health").value(200));  // Verifica que la salud sea 200
    // }
    // @PutMapping(path = "/character")
    // public ResponseEntity<Character> updateCharacter(@RequestBody Character character) {
    //     return new ResponseEntity<>(characterservice.updateOne(character),HttpStatus.OK);
    // }

    @Test
    public void testUpdateCharacter() {
        Character existingCharacter = new Character(1L, "Aiden", 10, 100);
        Character updatedCharacter = new Character(1L, "Hulk", 10, 100);
        
        when(characterService.getCharacterById(1L)).thenReturn(existingCharacter);
        when(characterService.updateOne(updatedCharacter)).thenReturn(updatedCharacter);

        ResponseEntity<Character> response = characterController.updateCharacter(updatedCharacter);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Hulk", response.getBody().getName());
        verify(characterService, times(1)).getCharacterById(1L);
        verify(characterService, times(1)).updateOne(updatedCharacter);

    }


}
