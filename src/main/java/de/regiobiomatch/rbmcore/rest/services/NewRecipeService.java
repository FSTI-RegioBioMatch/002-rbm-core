package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import de.regiobiomatch.rbmcore.rest.repositories.NewRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<NewRecipeModel> getRecipeById(String id) {
        return repository.findById(id);
    }

    public Page<NewRecipeModel> getRecipesByCompanyId(String companyId, Pageable pageable) {
        return repository.findByCompanyId(companyId, pageable);
    }

    public Page<NewRecipeModel> getRecipesByCompanyIdAndFilter(
            String companyId,
            String recipeName,
            String[] saisons,
            Pageable pageable
    ) {
        if (recipeName != null && !recipeName.isEmpty() && saisons != null && saisons.length > 0) {
            return repository.findByCompanyIdAndRecipeNameAndSaisonIn(companyId, recipeName, saisons, pageable);
        } else if (recipeName != null && !recipeName.isEmpty()) {
            return repository.findByCompanyIdAndRecipeName(companyId, recipeName, pageable);
        } else if (saisons != null && saisons.length > 0) {
            return repository.findByCompanyIdAndSaisonIn(companyId, saisons, pageable);
        } else {
            return repository.findByCompanyId(companyId, pageable);
        }
    }

    public ResponseEntity<?> deleteRecipeById(String id) {
        repository.deleteById(id);
        if (repository.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    public NewRecipeModel updateRecipe(NewRecipeDTO recipeDto, String id) {
        NewRecipeModel recipe = convertToEntity(recipeDto);
        recipe.setId(id);
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
        recipe.setCompanyId(dto.getCompanyId());
        return recipe;
    }
}
