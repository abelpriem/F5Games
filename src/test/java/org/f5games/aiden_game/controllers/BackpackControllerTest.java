package org.f5games.aiden_game.controllers;

import java.util.ArrayList;
import java.util.List;

import org.f5games.aiden_game.GameMenu;
import org.f5games.aiden_game.models.Backpack;
import org.f5games.aiden_game.models.GameObject;
import org.f5games.aiden_game.services.BackpackService;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BackpackController.class)
public class BackpackControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BackpackService backpackService;
    @MockBean
    private GameMenu gameMenu;

    @Test
    public void testRestrieveObjects() throws Exception{
        List<Backpack> mockBackpacks = new ArrayList<>();
        mockBackpacks.add(new Backpack(1L,GameObject.builder().id(1L).name("mockObject").description("mockDescription").special(true).build()));
        mockBackpacks.add(new Backpack(2L,GameObject.builder().id(2L).name("mockObject2").description("mockDescription2").special(true).build()));

        when(backpackService.getAll()).thenReturn(mockBackpacks);

        mockMvc.perform(get("/backpack"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].id").value(1))
            .andExpect(jsonPath("$[0].gameObject.id").value(1))
            .andExpect(jsonPath("$[0].gameObject.name").value("mockObject"))
            .andExpect(jsonPath("$[0].gameObject.description").value("mockDescription"))
            .andExpect(jsonPath("$[0].gameObject.special").value(true));
    }

    @Test
    public void testAddObject() throws Exception{
        Long objectId=1L;
        Long backpackId=1L;
        Backpack mockBackpack=new Backpack(backpackId,GameObject.builder().id(objectId).name("mockObject").description("mockDescription").special(true).build());
        when(backpackService.insertOne(objectId)).thenReturn(mockBackpack);

        mockMvc.perform(post("/backpack").param("objectId", String.valueOf(objectId)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(backpackId))
            .andExpect(jsonPath("$.gameObject.id").value(objectId))
            .andExpect(jsonPath("$.gameObject.name").value("mockObject"))
            .andExpect(jsonPath("$.gameObject.description").value("mockDescription"))
            .andExpect(jsonPath("$.gameObject.special").value(true));
    }

    @Test
    public void testUpdateObject() throws Exception{
        Long objectId=1L;
        Long backpackId=1L;
        Backpack mockBackpack=new Backpack(backpackId,GameObject.builder().id(objectId).name("mockObject").description("mockDescription").special(true).build());
        when(backpackService.updateOne(backpackId,objectId)).thenReturn(mockBackpack);

        mockMvc.perform(put("/backpack/{id}", backpackId).param("objectId", String.valueOf(objectId)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(backpackId))
            .andExpect(jsonPath("$.gameObject.id").value(objectId))
            .andExpect(jsonPath("$.gameObject.name").value("mockObject"))
            .andExpect(jsonPath("$.gameObject.description").value("mockDescription"))
            .andExpect(jsonPath("$.gameObject.special").value(true));
    }

    @Test
    public void testDeleteObject() throws Exception{
        Long backpackId=1L;
        doNothing().when(backpackService).deleteOne(backpackId);
        mockMvc.perform(delete("/backpack/{id}", backpackId))
            .andExpect(status().isNoContent());
    }
}
