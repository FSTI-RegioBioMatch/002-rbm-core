package de.regiobiomatch.rbmcore.rest.controllers;


import de.regiobiomatch.rbmcore.rest.models.recipe.dtos.CreateRecipeDTO;
import de.regiobiomatch.rbmcore.rest.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    RecipeService recipeService;


    @GetMapping
    public ResponseEntity<?> getRecipesByCurrentCompany(@RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        return ResponseEntity.ok(recipeService.getRecipesByCurrentCompany(currentCompany));
    }

    @PostMapping
    public ResponseEntity<?> createRecipe(@RequestBody CreateRecipeDTO createRecipeDTO, @RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        return ResponseEntity.ok(recipeService.createRecipe(createRecipeDTO, currentCompany));
    }

}
