package de.regiobiomatch.rbmcore.rest.models.newrecipe;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewRecipeDTO {

    private String recipeName;
    private String recipeDescription;
    private boolean includeInMenuPlanning;
    private boolean publishAsCommunityRecipe;
    private List<Step> steps;
    private List<NewIngredient> ingredients;
    private String energie;
    private String portionen;
    private String besonderheiten;
    private List<String> essensgaeste; // Changed to List<String> for multi-select
    private List<String> allergene;    // Changed to List<String> for multi-select
    private String saison;
    private List<String> diets;
    private String recipeImage;
    private String companyId;
}