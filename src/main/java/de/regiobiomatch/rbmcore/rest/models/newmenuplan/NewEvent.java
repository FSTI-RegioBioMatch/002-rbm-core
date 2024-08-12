package de.regiobiomatch.rbmcore.rest.models.newmenuplan;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEvent {
    private String id;
    private String start;
    private boolean allDay;
    private String description;
    private String location;
    private int portions;
    private int portionsVegetarisch;
    private int portionsVegan;
    private String repeatFrequency;
}