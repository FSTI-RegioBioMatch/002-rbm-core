package de.regiobiomatch.rbmcore.rest.models.shoppinglist;

import de.regiobiomatch.rbmcore.rest.models.recipe.subs.Ingredient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ShoppingListModel {

    private String id;
    private String companyId;
    private List<Ingredient> ingredients;
    private Date createdAt;

}
