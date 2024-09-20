package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MappedOffersIngredientsRepository extends MongoRepository<MappedOffersIngredientsModel, String> {
    Page<MappedOffersIngredientsModel> findByCompanyId(String companyId, Pageable pageable);
    // New method to find by shoppingListId
    Optional<MappedOffersIngredientsModel> findByIdAndCompanyId(String id, String companyId);
    List<MappedOffersIngredientsModel> findByShoppingListIdAndCompanyId(String shoppingListId, String companyId);
}
