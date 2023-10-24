package br.com.loops.controller;

import br.com.loops.model.Character;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(CharacterController.class)
public class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCharacter() throws Exception {
        Character mockCharacter = new Character();
        mockCharacter.setId(1);
        mockCharacter.setName("Rick Sanchez");

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/characters/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("character"))
                .andReturn();

        // Terminar depois
    }
}


