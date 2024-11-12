package de.regiobiomatch.rbmcore.rest.controllers;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.ShoppingListOffersPurchaseIntendPriceRequestTrackModel;
import de.regiobiomatch.rbmcore.rest.services.ShoppingListTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/shopping-list-track")
@CrossOrigin(
        origins = {"https://app.regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
public class ShoppingListTrackController {

    @Autowired
    private ShoppingListTrackService service;

    // Create or update a track record
    @PostMapping
    public ResponseEntity<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> saveOrUpdate(@RequestBody ShoppingListOffersPurchaseIntendPriceRequestTrackModel trackModel) {
        ShoppingListOffersPurchaseIntendPriceRequestTrackModel savedModel = service.saveOrUpdate(trackModel);
        return ResponseEntity.ok(savedModel);
    }

    // Get all track records
    @GetMapping
    public ResponseEntity<List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel>> findAll() {
        List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> tracks = service.findAll();
        return ResponseEntity.ok(tracks);
    }

    // Get track record by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> findById(@PathVariable String id) {
        Optional<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> trackModel = service.findById(id);
        return trackModel.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get track records by Shopping List ID
    @GetMapping("/shopping-list/{shoppingListId}")
    public ResponseEntity<List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel>> findByShoppingListId(@PathVariable String shoppingListId) {
        List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> tracks = service.findByShoppingListId(shoppingListId);
        return ResponseEntity.ok(tracks);
    }

    // Update a track record by ID
    @PutMapping("/{id}")
    public ResponseEntity<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> update(@PathVariable String id, @RequestBody ShoppingListOffersPurchaseIntendPriceRequestTrackModel trackModel) {
        if (service.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        trackModel.setId(id); // Ensure ID consistency
        ShoppingListOffersPurchaseIntendPriceRequestTrackModel updatedModel = service.saveOrUpdate(trackModel);
        return ResponseEntity.ok(updatedModel);
    }

    // Delete a track record by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
