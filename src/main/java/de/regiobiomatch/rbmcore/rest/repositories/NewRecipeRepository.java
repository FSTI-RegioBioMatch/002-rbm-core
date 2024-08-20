package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface NewRecipeRepository extends MongoRepository<NewRecipeModel, String> {
    Page<NewRecipeModel> findByCompanyId(String companyId, Pageable pageable);

    @Query("{'companyId': ?0, 'recipeName': { $regex: ?1, $options: 'i' }}")
    Page<NewRecipeModel> findByCompanyIdAndRecipeName(String companyId, String recipeName, Pageable pageable);

    @Query("{'companyId': ?0, 'saison': { $in: ?1 }}")
    Page<NewRecipeModel> findByCompanyIdAndSaisonIn(String companyId, String[] saison, Pageable pageable);

    @Query("{'companyId': ?0, 'recipeName': { $regex: ?1, $options: 'i' }, 'saison': { $in: ?2 }}")
    Page<NewRecipeModel> findByCompanyIdAndRecipeNameAndSaisonIn(String companyId, String recipeName, String[] saison, Pageable pageable);

}
