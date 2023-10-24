package br.com.loops.controller;

import br.com.loops.model.Character;
import br.com.loops.model.Location;
import com.google.gson.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import com.google.gson.JsonArray;

import java.util.ArrayList;

@Controller
public class CharacterController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/character")
    public String getCharacter(int id, Model model) {
        String apiUrl = "https://rickandmortyapi.com/api/character/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        String responseBody = responseEntity.getBody();

        //System.out.println(responseBody);

        // Para separar Location, Origin e Episode parseia a String pra JSON
        JsonObject jsonResponse = new JsonParser().parse(responseBody).getAsJsonObject();

        JsonArray episodeJsonArray = jsonResponse.getAsJsonArray("episode");
        ArrayList<String> episodes = new ArrayList<>();
        for (JsonElement episodeElement : episodeJsonArray) {
            String episodeUrl = episodeElement.getAsString();
            String episodeNumber = episodeUrl.substring(episodeUrl.lastIndexOf('/') + 1);
            episodes.add(episodeNumber);
        }

        Character character = new Character();

        // Usar jsonResponse pra pegar os atributos comuns

        character.setId(jsonResponse.get("id").getAsInt());
        character.setName(jsonResponse.get("name").getAsString());
        character.setStatus(jsonResponse.get("status").getAsString());
        character.setSpecies(jsonResponse.get("species").getAsString());
        character.setType(jsonResponse.get("type").getAsString());
        character.setGender(jsonResponse.get("gender").getAsString());
        character.setImage(jsonResponse.get("image").getAsString());

        // Usar jsonResponse pra pegar os objetos

        JsonObject originJson = jsonResponse.getAsJsonObject("origin");
        Location origin = new Location();
        origin.setName(originJson.get("name").getAsString());
        origin.setUrl(originJson.get("url").getAsString());
        character.setOrigin(origin);

        JsonObject locationJson = jsonResponse.getAsJsonObject("location");
        Location location = new Location();
        location.setName(locationJson.get("name").getAsString());
        location.setUrl(locationJson.get("url").getAsString());
        character.setLocation(location);

        // Seta o array de episódios
        character.setEpisodes(episodes);

        model.addAttribute("character", character);
        return "character";
    }
}
