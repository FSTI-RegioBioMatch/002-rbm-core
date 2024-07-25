package de.regiobiomatch.rbmcore.rest.models.newrecipe;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alternative {
    private String name;
    private String amount;
    private String unit;
}