package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import de.regiobiomatch.rbmcore.rest.services.NewRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/new-recipes")
public class NewRecipeController {
    private final NewRecipeService service;

    @Autowired
    public NewRecipeController(NewRecipeService service) {
        this.service = service;
    }

    @PostMapping
    public NewRecipeModel createRecipe(@RequestBody NewRecipeDTO recipeDto) {
        return service.saveRecipe(recipeDto);
    }
}
