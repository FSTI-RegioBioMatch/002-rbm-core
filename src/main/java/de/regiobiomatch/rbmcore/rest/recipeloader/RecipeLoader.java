package de.regiobiomatch.rbmcore.rest.recipeloader;

import de.regiobiomatch.rbmcore.rest.models.recipe.RecipeModel;
import de.regiobiomatch.rbmcore.rest.models.recipe.subs.Description;
import de.regiobiomatch.rbmcore.rest.models.recipe.subs.Ingredient;
import de.regiobiomatch.rbmcore.rest.services.RecipeService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.core.json.JsonReadFeature;

@Log
@Configuration
public class RecipeLoader {

  @Value("${isLoadData}")
  private boolean isLoadData;

  @Value("classpath:recipes.json")
  private Resource resource;

  @Autowired
  RecipeService recipeService;

  @Bean
  CommandLineRunner loadRecipes(RecipeService recipeService) {
    return args -> {
      if (isLoadData) {
        log.info("Loading recipes from JSON file");
        ObjectMapper objectMapper = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature())
            .build();
        JsonNode root;
        try {
          root = objectMapper.readTree(resource.getFile());
        } catch (IOException e) {
          log.severe("Failed to read JSON file: " + e.getMessage());
          return;
        }

        List<RecipeModel> recipes = new ArrayList<>();

        for (JsonNode node : root) {
          try {
            if (containsNaN(node)) {
              log.warning("Skipping recipe due to NaN value: " + node.get("title").asText());
              continue;
            }

            RecipeModel recipe = new RecipeModel();
            recipe.setId(UUID.randomUUID().toString());
            recipe.setPublic(true); // Assuming the recipes are public
            recipe.setImageUrls(objectMapper.convertValue(node.get("image_urls"), List.class));
            recipe.setPortions(node.get("portions").asInt());
            recipe.setTitle(node.get("title").asText());
            recipe.setCompanyId("defaultCompanyId"); // Assuming a default company ID

            // Parsing and cleaning ingredients
            List<Ingredient> ingredients = new ArrayList<>();
            String ingredientsStr = node.get("ingredients").asText();
            JsonNode ingredientsNode = objectMapper.readTree(ingredientsStr);
            for (JsonNode ingNode : ingredientsNode) {
              Ingredient ingredient = new Ingredient();
              ingredient.setName(ingNode.get("name").asText());
              ingredient.setAmount(ingNode.get("quantity").asText());
              ingredient.setUnit(""); // Assuming unit field is not available in JSON
              ingredient.setProcessing("");
              ingredient.setPlace("");
              ingredient.setCreatorPersonName("");
              ingredients.add(ingredient);
            }
            recipe.setIngredients(ingredients);

            // Parsing and cleaning instructions
            List<Description> descriptions = new ArrayList<>();
            String[] steps = node.get("instructions").asText().split("\\. ");
            for (int i = 0; i < steps.length; i++) {
              Description description = new Description();
              description.setStep(i + 1);
              description.setTitle("Step " + (i + 1));
              description.setDescription(steps[i].replaceAll("\\\\", ""));
              descriptions.add(description);
            }
            recipe.setDescription(descriptions);

            recipes.add(recipe);
          } catch (Exception e) {
            log.severe("Error processing recipe: " + node.get("title").asText() + " - " + e.getMessage());
          }
        }

        recipeService.deleteAllAndSaveAll(recipes);
      } else {
        log.info("Skipping loading recipes from JSON file");
      }
    };
  }

  private boolean containsNaN(JsonNode node) {
    if (node.isTextual() && node.asText().equals("NaN")) {
      return true;
    }
    if (node.isNumber() && Double.isNaN(node.doubleValue())) {
      return true;
    }
    for (JsonNode child : node) {
      if (containsNaN(child)) {
        return true;
      }
    }
    return false;
  }
}
