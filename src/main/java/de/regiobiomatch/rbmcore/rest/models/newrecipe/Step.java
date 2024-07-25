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
public class Step {
    private String schritt;
    private String anleitung;
    private List<String> images;
}