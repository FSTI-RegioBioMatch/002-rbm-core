package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsDTO;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import de.regiobiomatch.rbmcore.rest.services.MappedOffersIngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(
        origins = {"https://regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
@RequestMapping("/api/v1/mapped-offers-ingredients")
public class MappedOffersIngredientsController {
    private final MappedOffersIngredientsService service;

    @Autowired
    public MappedOffersIngredientsController(MappedOffersIngredientsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createMappedOffersIngredients(@RequestBody MappedOffersIngredientsDTO dto, @RequestHeader("Current-Company") String currentCompany) {
        if (dto.getShoppingListId() == null || dto.getShoppingListId().isEmpty()) {
            return ResponseEntity.badRequest().body("Shopping List ID must be provided");
        }

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }
        dto.setCompanyId(currentCompany);
        MappedOffersIngredientsModel savedModel = service.saveMappedOffersIngredients(dto);
        return ResponseEntity.ok(savedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MappedOffersIngredientsModel> getMappedOffersIngredientsById(
            @PathVariable String id,
            @RequestHeader("Current-Company") String currentCompany) {

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        Optional<MappedOffersIngredientsModel> modelOptional = service.getMappedOffersIngredientsById(id);

        return modelOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Page<MappedOffersIngredientsModel>> getMappedOffersIngredientsByCompanyId(
            @RequestParam String companyId,
            @PageableDefault(sort = "shoppingListId", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestHeader("Current-Company") String currentCompany) {

        if (companyId == null || companyId.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Body is null in case of error.
        }

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        Page<MappedOffersIngredientsModel> models = service.getMappedOffersIngredientsByCompanyId(companyId, pageable);
        return ResponseEntity.ok(models);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMappedOffersIngredientsById(@PathVariable String id, @RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }
        return service.deleteMappedOffersIngredientsById(id);
    }

    @PutMapping("/{id}")
    public MappedOffersIngredientsModel updateMappedOffersIngredients(@PathVariable String id, @RequestBody MappedOffersIngredientsDTO dto, @RequestHeader("Current-Company") String currentCompany) {
        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }
        return service.updateMappedOffersIngredients(dto, id);
    }

    // New endpoint to get mapped offers by shoppingListId
    @GetMapping("/shopping-list/{shoppingListId}")
    public ResponseEntity<List<MappedOffersIngredientsModel>> getMappedOffersIngredientsByShoppingListId(
            @PathVariable String shoppingListId,
            @RequestHeader("Current-Company") String currentCompany) {

        if (shoppingListId == null || shoppingListId.isEmpty()) {
            return ResponseEntity.badRequest().body(null); // Body is null in case of error.
        }

        if (currentCompany == null) {
            throw new IllegalArgumentException("Current-Company header is required");
        }

        List<MappedOffersIngredientsModel> models = service.getMappedOffersIngredientsByShoppingListId(shoppingListId);
        if (models.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(models);
    }
}