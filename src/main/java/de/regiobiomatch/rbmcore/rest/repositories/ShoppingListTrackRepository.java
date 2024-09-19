package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.ShoppingListOffersPurchaseIntendPriceRequestTrackModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShoppingListTrackRepository extends MongoRepository<ShoppingListOffersPurchaseIntendPriceRequestTrackModel, String> {
    List<ShoppingListOffersPurchaseIntendPriceRequestTrackModel> findByShoppingListId(String shoppingListId);
}
