package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import de.regiobiomatch.rbmcore.rest.repositories.NewRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewRecipeService {
    private final NewRecipeRepository repository;

    @Autowired
    public NewRecipeService(NewRecipeRepository repository) {
        this.repository = repository;
    }

    public NewRecipeModel saveRecipe(NewRecipeDTO recipeDto) {
        NewRecipeModel recipe = convertToEntity(recipeDto);
        return repository.save(recipe);
    }

    private NewRecipeModel convertToEntity(NewRecipeDTO dto) {
        NewRecipeModel recipe = new NewRecipeModel();
        recipe.setRecipeName(dto.getRecipeName());
        recipe.setRecipeDescription(dto.getRecipeDescription());
        recipe.setIncludeInMenuPlanning(dto.isIncludeInMenuPlanning());
        recipe.setPublishAsCommunityRecipe(dto.isPublishAsCommunityRecipe());
        recipe.setSteps(dto.getSteps());
        recipe.setIngredients(dto.getIngredients());
        recipe.setEnergie(dto.getEnergie());
        recipe.setPortionen(dto.getPortionen());
        recipe.setBesonderheiten(dto.getBesonderheiten());
        recipe.setEssensgaeste(dto.getEssensgaeste());
        recipe.setAllergene(dto.getAllergene());
        recipe.setSaison(dto.getSaison());
        recipe.setDiets(dto.getDiets());
        recipe.setRecipeImage(dto.getRecipeImage());
        return recipe;
    }
}
