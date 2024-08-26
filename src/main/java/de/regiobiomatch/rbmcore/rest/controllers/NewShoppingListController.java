package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.NewShoppingListModel;
import de.regiobiomatch.rbmcore.rest.services.NewShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(
        origins = {"https://regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
@RequestMapping("/api/v1/shoppinglists")
public class NewShoppingListController {

    @Autowired
    private NewShoppingListService newShoppingListService;

    @PostMapping
    public ResponseEntity<NewShoppingListModel> createShoppingList(@RequestBody NewShoppingListModel newShoppingListModel) {
        NewShoppingListModel createdShoppingList = newShoppingListService.createShoppingList(newShoppingListModel);
        return ResponseEntity.ok(createdShoppingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewShoppingListModel> getShoppingListById(
            @PathVariable String id,
            @RequestParam("companyId") String companyId) {
        System.out.println("id: " + id);
        System.out.println("companyId: " + companyId);
        return newShoppingListService.getShoppingListByIdAndCompanyId(id, companyId);
    }



    @GetMapping
    public ResponseEntity<List<NewShoppingListModel>> getAllShoppingListsByCompanyId(
            @RequestHeader("Current-Company") String companyId) {
        List<NewShoppingListModel> shoppingLists = newShoppingListService.getAllShoppingListsByCompanyId(companyId);
        return ResponseEntity.ok(shoppingLists);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShoppingListById(
            @PathVariable String id,
            @RequestHeader("Current-Company") String companyId) {
        return newShoppingListService.deleteShoppingListByIdAndCompanyId(id, companyId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShoppingList(
            @PathVariable String id,
            @RequestBody NewShoppingListModel updatedShoppingList,
            @RequestHeader("Current-Company") String companyId) {
        return newShoppingListService.updateShoppingList(id, updatedShoppingList, companyId);
    }
}
