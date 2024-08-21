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

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"https://regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
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

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        NewRecipeModel savedRecipe = service.saveRecipe(recipeDto);
        return ResponseEntity.ok(savedRecipe);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewRecipeModel> getRecipeById(@PathVariable String id, @RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }
        return service.getRecipeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getRecipesByCompanyId(
            @RequestParam String companyId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> saisons,
            @PageableDefault(sort = "recipeName", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestHeader("Current-Company") String currentCompany) {
        if (companyId == null || companyId.isEmpty()) {
            return ResponseEntity.badRequest().body("Company ID must be provided");
        }

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        String[] saisonsArray = saisons != null ? saisons.toArray(new String[0]) : new String[0];
        Page<NewRecipeModel> recipes = service.getRecipesByCompanyIdAndFilter(companyId, name, saisonsArray, pageable);
        return ResponseEntity.ok(recipes);
    }
}