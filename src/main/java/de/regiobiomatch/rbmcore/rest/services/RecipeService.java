package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.recipe.dtos.CreateRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.recipe.RecipeModel;
import de.regiobiomatch.rbmcore.rest.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RecipeService {

    @Autowired
    RecipeRepository recipeRepository;


    public RecipeModel getRecipeById(String id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public List<RecipeModel> getRecipesByCurrentCompany(String currentCompany) {
        return recipeRepository.findByCompanyId(currentCompany);
    }

    public RecipeModel createRecipe(CreateRecipeDTO createRecipeDTO, String currentCompany) {
        RecipeModel recipe = new RecipeModel();

        recipe.setId(UUID.randomUUID().toString());
        recipe.setPublic(createRecipeDTO.isPublic());
        recipe.setImageUrls(createRecipeDTO.getImageUrls());
        recipe.setIngredients(createRecipeDTO.getIngredients());
        recipe.setPortions(createRecipeDTO.getPortions());
        recipe.setTitle(createRecipeDTO.getTitle());
        recipe.setDescription(createRecipeDTO.getDescription());
        recipe.setCompanyId(currentCompany);




        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(String id) {
        recipeRepository.deleteById(id);
    }

    public void deleteAllAndSaveAll(List<RecipeModel> recipes) {
        recipeRepository.deleteAll();
        recipeRepository.saveAll(recipes);
    }
}
