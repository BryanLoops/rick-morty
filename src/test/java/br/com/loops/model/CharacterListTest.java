package br.com.loops.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import java.util.List;

class CharacterListTest {

    @Test
    void testGetInfo() {
        Info info = new Info();
        CharacterList characterList = new CharacterList();
        characterList.setInfo(info);

        assertEquals(info, characterList.getInfo());
    }

    @Test
    void testGetResults() {
        List<Character> characters = List.of(new Character(), new Character());
        CharacterList characterList = new CharacterList();
        characterList.setResults(characters);

        assertEquals(characters, characterList.getResults());
    }

    @Test
    void testSetInfo() {
        Info info = new Info();
        CharacterList characterList = new CharacterList();
        characterList.setInfo(info);

        assertEquals(info, characterList.getInfo());
    }

    @Test
    void testSetResults() {
        List<Character> characters = List.of(new Character(), new Character());
        CharacterList characterList = new CharacterList();
        characterList.setResults(characters);

        assertEquals(characters, characterList.getResults());
    }
}

