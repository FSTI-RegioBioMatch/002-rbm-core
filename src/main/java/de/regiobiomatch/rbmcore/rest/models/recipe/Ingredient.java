package de.regiobiomatch.rbmcore.rest.models.recipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ingredient {

    private String name;
    private String amount;
    private String unit;
    private String processing;
    private String place;
    private String creatorPersonName;
}
