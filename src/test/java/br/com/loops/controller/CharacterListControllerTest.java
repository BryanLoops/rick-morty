package br.com.loops.controller;

import br.com.loops.model.CharacterList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(CharacterListController.class)
class CharacterListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    void getCharacterList() throws Exception {
        // Mock da API
        CharacterList mockCharacterList = new CharacterList();
        when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(CharacterList.class))).thenReturn(mockCharacterList);

        // Requisição
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/characterList"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(view().name("characterList"))
                .andExpect(model().attributeExists("characters", "processedPage", "nextPage", "prevPage"))
                .andReturn();

        ModelAndView modelAndView = result.getModelAndView();

        // Atributos do modelo
        CharacterList characters = (CharacterList) modelAndView.getModel().get("characters");
        String processedPage = (String) modelAndView.getModel().get("processedPage");
        int pageNumber = (int) modelAndView.getModel().get("pageNumber");
        int nextPage = (int) modelAndView.getModel().get("nextPage");
        int prevPage = (int) modelAndView.getModel().get("prevPage");

        // Testes
        assertEquals(mockCharacterList.getClass(), characters.getClass());
        assertEquals("1", processedPage);
        assertEquals(1, pageNumber);
        assertEquals(2, nextPage);
        assertEquals(1, prevPage);
    }
}


