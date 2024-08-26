package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.shoppinglist.dtos.CreateShoppingListDTO;
import de.regiobiomatch.rbmcore.rest.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = {"https://regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
@RestController
@RequestMapping("/api/v1/shopping-list")
public class ShoppingListController {

    @Autowired
    ShoppingListService shoppingListService;

    @GetMapping
    public ResponseEntity<?> getShoppingList(@RequestHeader("Current-Company") String currentCompany) {
        return ResponseEntity.ok(shoppingListService.getShoppingListByCurrentCompany(currentCompany));
    }

    @PostMapping
    public ResponseEntity<?> createShoppingList(@RequestHeader("Current-Company") String currentCompany, @RequestBody CreateShoppingListDTO createShoppingListDTO) {
        return ResponseEntity.ok(shoppingListService.createShoppingList(currentCompany, createShoppingListDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateShoppingList(@RequestHeader("Current-Company") String currentCompany, @RequestBody CreateShoppingListDTO createShoppingListDTO, @PathVariable String id) {
        return ResponseEntity.ok(shoppingListService.updateShoppingList(currentCompany, createShoppingListDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShoppingListById(@RequestHeader("Current-Company") String currentCompany, @PathVariable String id) {
        return ResponseEntity.ok(shoppingListService.getShoppingListById(currentCompany, id));
    }
}
