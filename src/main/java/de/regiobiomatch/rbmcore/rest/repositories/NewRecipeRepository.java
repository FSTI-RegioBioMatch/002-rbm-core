package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewRecipeRepository extends MongoRepository<NewRecipeModel, String> {
    Page<NewRecipeModel> findByCompanyId(String companyId, Pageable pageable);
}
