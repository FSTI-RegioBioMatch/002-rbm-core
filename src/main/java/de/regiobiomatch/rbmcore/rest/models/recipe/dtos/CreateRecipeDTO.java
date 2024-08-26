package de.regiobiomatch.rbmcore.rest.models.recipe.dtos;

import de.regiobiomatch.rbmcore.rest.models.recipe.Description;
import de.regiobiomatch.rbmcore.rest.models.recipe.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateRecipeDTO {

    private boolean isPublic;
    private List<String> imageUrls;
    private List<Ingredient> ingredients;
    private int portions;
    private String title;
    private List<Description> description;

}
