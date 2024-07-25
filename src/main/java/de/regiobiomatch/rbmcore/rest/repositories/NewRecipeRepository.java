package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewRecipeRepository extends MongoRepository<NewRecipeModel, String> {
}