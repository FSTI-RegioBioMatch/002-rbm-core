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
    public ResponseEntity<?> createRecipe(@RequestBody NewRecipeDTO recipeDto, @RequestHeader("Current-Company") String currentCompany) {
        if (recipeDto.getCompanyId() == null || recipeDto.getCompanyId().isEmpty()) {
            return ResponseEntity.badRequest().body("Company ID must be provided");
        }

        NewRecipeModel savedRecipe = service.saveRecipe(recipeDto);
        return ResponseEntity.ok(savedRecipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewRecipeModel> getRecipeById(@PathVariable String id, @RequestHeader("Current-Company") String currentCompany) {
        return service.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getRecipesByCompanyId(
            @RequestParam String companyId,
            @PageableDefault(sort = "recipeName", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestHeader("Current-Company") String currentCompany) {
        if (companyId == null || companyId.isEmpty()) {
            return ResponseEntity.badRequest().body("Company ID must be provided");
        }

        Page<NewRecipeModel> recipes = service.getRecipesByCompanyId(companyId, pageable);
        return ResponseEntity.ok(recipes);
    }
}
