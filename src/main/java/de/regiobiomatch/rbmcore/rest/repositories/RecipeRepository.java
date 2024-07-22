package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.recipe.RecipeModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RecipeRepository extends MongoRepository<RecipeModel, String> {

    List<RecipeModel> findByCompanyId(String companyId);

}
