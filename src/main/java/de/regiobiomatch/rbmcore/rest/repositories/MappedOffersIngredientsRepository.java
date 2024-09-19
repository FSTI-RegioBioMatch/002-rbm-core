package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MappedOffersIngredientsRepository extends MongoRepository<MappedOffersIngredientsModel, String> {
    Page<MappedOffersIngredientsModel> findByCompanyId(String companyId, Pageable pageable);
}
