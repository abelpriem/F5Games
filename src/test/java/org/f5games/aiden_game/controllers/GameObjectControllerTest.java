package org.f5games.aiden_game.controllers;

import java.util.ArrayList;
import java.util.List;

import org.f5games.aiden_game.GameMenu;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.GameObjectService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GameObjectController.class)
public class GameObjectControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GameObjectService gameObjectService;
    @MockBean
    private GameMenu gameMenu;

    @Test
    public void testGetObjects() throws Exception{
        List<GameObject> mockGameObjects = new ArrayList<>();
        mockGameObjects.add(GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build());
        mockGameObjects.add(GameObject.builder().id(2L).name("mockObject2").description("mockDescription2").special(true).build());

        when(gameObjectService.getAll()).thenReturn(mockGameObjects);

        mockMvc.perform(get("/objects"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].name").value("mockObject"))
            .andExpect(jsonPath("$[0].description").value("mockDescription"))
            .andExpect(jsonPath("$[0].special").value(true));
    }
}
