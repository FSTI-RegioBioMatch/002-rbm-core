package de.regiobiomatch.rbmcore.rest.models.shoppinglist.subs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SoppingListIngredient {

    private String name;
    private String amount;
    private String unit;
    private String place;

}
