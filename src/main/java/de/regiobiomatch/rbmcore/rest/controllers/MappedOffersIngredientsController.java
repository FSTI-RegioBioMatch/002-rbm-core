package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsDTO;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import de.regiobiomatch.rbmcore.rest.services.MappedOffersIngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public ResponseEntity<?> createMappedOffersIngredients(
            @RequestBody MappedOffersIngredientsDTO dto,
            @RequestHeader("Current-Company") String currentCompany) {

        if (dto.getShoppingListId() == null || dto.getShoppingListId().isEmpty()) {
            return ResponseEntity.badRequest().body("Shopping List ID must be provided");
        }

        if (currentCompany == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Current-Company header is required");
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }

        Optional<MappedOffersIngredientsModel> modelOptional = service.getMappedOffersIngredientsById(id, currentCompany);

        if (modelOptional.isPresent()) {
            return ResponseEntity.ok(modelOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    public ResponseEntity<Page<MappedOffersIngredientsModel>> getMappedOffersIngredientsByCompanyId(
            @RequestParam String companyId,
            @PageableDefault(sort = "shoppingListId", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestHeader("Current-Company") String currentCompany) {

        if (companyId == null || companyId.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        if (currentCompany == null || !currentCompany.equals(companyId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }

        Page<MappedOffersIngredientsModel> models = service.getMappedOffersIngredientsByCompanyId(companyId, pageable);
        return ResponseEntity.ok(models);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMappedOffersIngredientsById(
            @PathVariable String id,
            @RequestHeader("Current-Company") String currentCompany) {

        if (currentCompany == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Current-Company header is required");
        }

        return service.deleteMappedOffersIngredientsById(id, currentCompany);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMappedOffersIngredients(
            @PathVariable String id,
            @RequestBody MappedOffersIngredientsDTO dto,
            @RequestHeader("Current-Company") String currentCompany) {

        if (currentCompany == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Current-Company header is required");
        }

        Optional<MappedOffersIngredientsModel> updatedModel = service.updateMappedOffersIngredients(dto, id, currentCompany);

        if (updatedModel.isPresent()) {
            return ResponseEntity.ok(updatedModel.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Endpoint to get mapped offers by shoppingListId
    @GetMapping("/shopping-list/{shoppingListId}")
    public ResponseEntity<List<MappedOffersIngredientsModel>> getMappedOffersIngredientsByShoppingListId(
            @PathVariable String shoppingListId,
            @RequestHeader("Current-Company") String currentCompany) {

        if (shoppingListId == null || shoppingListId.isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }

        if (currentCompany == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.emptyList());
        }

        List<MappedOffersIngredientsModel> models = service.getMappedOffersIngredientsByShoppingListIdAndCompanyId(shoppingListId, currentCompany);

        if (models.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(models);
    }
}
