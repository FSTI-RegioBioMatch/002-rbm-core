package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.ShoppingListOffersPurchaseIntendPriceRequestTrackModel;
import de.regiobiomatch.rbmcore.rest.repositories.ShoppingListTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListTrackService {

    @Autowired
    private ShoppingListTrackRepository repository;

    // Create or update a track record
    public ShoppingListOffersPurchaseIntendPriceRequestTrackModel saveOrUpdate(ShoppingListOffersPurchaseIntendPriceRequestTrackModel trackModel) {
        return repository.save(trackModel);
    }

    // Get all track records
    public List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> findAll() {
        return repository.findAll();
    }

    // Get track record by ID
    public Optional<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> findById(String id) {
        return repository.findById(id);
    }

    // Get track records by Shopping List ID
    public List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> findByShoppingListId(String shoppingListId) {
        return repository.findByShoppingListId(shoppingListId);
    }
    // Delete a track record by ID
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
