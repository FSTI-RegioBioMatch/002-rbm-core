package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import de.regiobiomatch.rbmcore.rest.services.NewRecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<NewRecipeModel> createRecipe(@RequestBody NewRecipeDTO recipeDto) {
        NewRecipeModel savedRecipe = service.saveRecipe(recipeDto);
        return ResponseEntity.ok(savedRecipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewRecipeModel> getRecipeById(@PathVariable String id) {
        return service.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<NewRecipeModel>> getRecipesByCompanyId(
            @RequestParam String companyId,
            @PageableDefault(sort = "recipeName", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<NewRecipeModel> recipes = service.getRecipesByCompanyId(companyId, pageable);
        return ResponseEntity.ok(recipes);
    }
}
