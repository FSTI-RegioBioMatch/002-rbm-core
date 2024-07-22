package de.regiobiomatch.rbmcore.rest.models.shoppinglist.dtos;

import de.regiobiomatch.rbmcore.rest.models.recipe.subs.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateShoppingListDTO {

    private List<Ingredient> ingredients;
}
