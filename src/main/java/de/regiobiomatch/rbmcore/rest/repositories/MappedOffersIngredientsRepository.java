package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MappedOffersIngredientsRepository extends MongoRepository<MappedOffersIngredientsModel, String> {
    Page<MappedOffersIngredientsModel> findByCompanyId(String companyId, Pageable pageable);
    // New method to find by shoppingListId
    List<MappedOffersIngredientsModel> findByShoppingListId(String shoppingListId);
}
