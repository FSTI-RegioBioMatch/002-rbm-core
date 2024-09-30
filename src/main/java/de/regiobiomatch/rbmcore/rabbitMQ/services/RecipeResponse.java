package de.regiobiomatch.rbmcore.rabbitMQ.services;

import de.regiobiomatch.rbmcore.rest.models.newrecipe.NewRecipeModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
@ToString
public class RecipeResponse {
  private List<NewRecipeModel> recipes;
}
