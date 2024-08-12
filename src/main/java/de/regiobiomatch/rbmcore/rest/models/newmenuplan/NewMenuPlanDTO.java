package de.regiobiomatch.rbmcore.rest.models.newmenuplan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewMenuPlanDTO {
    private String id;
    private String name;
    private String nextExecution;
    private int weekday;
    private String repeatFrequency;
    private String location;
    private int portions;
    private int portionsVegetarisch;
    private int portionsVegan;
    private String description;
    private List<NewRecipe> recipes;
    private List<NewEvent> events;
    private String companyId;
}