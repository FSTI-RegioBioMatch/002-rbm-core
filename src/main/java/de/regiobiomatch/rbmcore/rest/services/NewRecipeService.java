package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rabbitMQ.services.RabbitRecipeService;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeDTO;
import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import de.regiobiomatch.rbmcore.rest.repositories.NewRecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NewRecipeService {
    private final NewRecipeRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(NewRecipeService.class);

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
        Page<NewRecipeModel> recipes = null;

        if (recipeName != null && !recipeName.isEmpty() && saisons != null && saisons.length > 0) {
            recipes = repository.findByCompanyIdAndRecipeNameAndSaisonIn(companyId, recipeName, saisons, pageable);
        } else if (recipeName != null && !recipeName.isEmpty()) {
            recipes = repository.findByCompanyIdAndRecipeName(companyId, recipeName, pageable);
        } else if (saisons != null && saisons.length > 0) {
            recipes = repository.findByCompanyIdAndSaisonIn(companyId, saisons, pageable);
        } else {
            recipes = repository.findByCompanyId(companyId, pageable);
        }

        // Clear images from each step in each recipe
        recipes.forEach(recipe -> {
            if (recipe.getSteps() != null) {
                recipe.getSteps().forEach(step -> step.setImages(null)); // or step.setImages(Collections.emptyList());
            }
        });

        return recipes;
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

    public List<String> getStepImagesByRecipeIdAndStepIndex(String recipeId, int stepIndex) {
        Optional<NewRecipeModel> recipeOptional = repository.findById(recipeId);
        if (recipeOptional.isPresent()) {
            NewRecipeModel recipe = recipeOptional.get();
            if (recipe.getSteps() != null && stepIndex < recipe.getSteps().size()) {
                return recipe.getSteps().get(stepIndex).getImages();
            }
        }
        return Collections.emptyList(); // Return an empty list if not found
    }

    public List<NewRecipeModel> getAllRecipes() {
        List<NewRecipeModel> recipes = repository.findAll();
        logger.info("Retrieved all recipes: {}", recipes);

        return recipes;
    }
}
