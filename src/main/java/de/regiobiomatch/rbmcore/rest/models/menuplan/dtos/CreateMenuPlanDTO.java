package de.regiobiomatch.rbmcore.rest.models.menuplan.dtos;

import de.regiobiomatch.rbmcore.rest.models.menuplan.subs.AvailableEnum;
import de.regiobiomatch.rbmcore.rest.models.recipe.RecipeModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateMenuPlanDTO {

    private String description;
    private String menuName;
    private String weekDay;
    private int executionWeekNumber;
    private String place;
    private int portions;
    private List<RecipeModel> recipes;
}
