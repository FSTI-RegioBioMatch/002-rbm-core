package de.regiobiomatch.rbmcore.rest.models.menuplan;

import de.regiobiomatch.rbmcore.rest.models.recipe.RecipeModel;
import de.regiobiomatch.rbmcore.rest.models.menuplan.subs.AvailableEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Document(collection = "menuplan")
public class MenuPlanModel {

    @Id
    private String id;
    private String companyId;
    private String description;
    private String menuName;
    private String weekDay;
    private int executionWeekNumber;
    private String place;
    private int portions;
    private Date lastChecked;
    private Date createdAt;
    private AvailableEnum available;
    private boolean isMarkedForPurchase;

    @DBRef
    private List<RecipeModel> recipes;

}
