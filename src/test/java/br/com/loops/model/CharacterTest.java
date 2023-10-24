package br.com.loops.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class CharacterTest {

    @Test
    void testGetId() {
        int id = 123;
        Character character = new Character();
        character.setId(id);

        assertEquals(id, character.getId());
    }

    @Test
    void testGetName() {
        String name = "John Doe";
        Character character = new Character();
        character.setName(name);

        assertEquals(name, character.getName());
    }

    @Test
    void testGetStatus() {
        String status = "Alive";
        Character character = new Character();
        character.setStatus(status);

        assertEquals(status, character.getStatus());
    }

    // Terminar depois

    @Test
    void testGetEpisodes() {
        List<String> episodes = List.of("Episode 1", "Episode 2");
        Character character = new Character();
        character.setEpisodes(new ArrayList<>(episodes));

        assertEquals(episodes, character.getEpisodes());
    }

    @Test
    void testSetEpisodes() {
        List<String> episodes = List.of("Episode 1", "Episode 2");
        Character character = new Character();
        character.setEpisodes(new ArrayList<>(episodes));

        assertEquals(episodes, character.getEpisodes());
    }
}

