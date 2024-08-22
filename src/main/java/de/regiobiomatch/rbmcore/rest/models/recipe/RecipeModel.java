package de.regiobiomatch.rbmcore.rest.models.recipe;

import de.regiobiomatch.rbmcore.rest.models.recipe.Description;
import de.regiobiomatch.rbmcore.rest.models.recipe.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "recipe")
public class RecipeModel {

    @Id
    private String id;
    private boolean isPublic;
    private List<String> imageUrls;
    private List<Ingredient> ingredients;
    private int portions;
    private String title;
    private List<Description> description;
    private String companyId;

}
