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
public class NewIngredient {
    private String name;
    private String amount;
    private String unit;
    private boolean optional;
    private String note;
    private List<Alternative> alternatives;
    private String nearbuyId;
}