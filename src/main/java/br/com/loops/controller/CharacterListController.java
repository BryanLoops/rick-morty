package br.com.loops.controller;

import br.com.loops.model.CharacterList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/characterList")
public class CharacterListController {

    @GetMapping
    public ModelAndView getCharacterList(@RequestParam(defaultValue = "1") int page) {
        String apiUrl = "https://rickandmortyapi.com/api/character/?page=" + page;
        RestTemplate restTemplate = new RestTemplate();
        CharacterList characterList = restTemplate.getForObject(apiUrl, CharacterList.class);

        String processedPage = Integer.toString(page);

        ModelAndView modelAndView = new ModelAndView("characterList");
        modelAndView.addObject("characters", characterList);
        modelAndView.addObject("processedPage", processedPage);

        // Calcula o número da próxima página
        int pageNumber = page;
        int nextPage = page + 1;
        int prevPage = (page > 1) ? page - 1 : 1;
        modelAndView.addObject("pageNumber", pageNumber);
        modelAndView.addObject("nextPage", nextPage);
        modelAndView.addObject("prevPage", prevPage);

        return modelAndView;
    }
}
