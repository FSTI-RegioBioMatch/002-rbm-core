package de.regiobiomatch.rbmcore.rest.models.newshoppinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "new_shopping_list")
public class NewShoppingListModel {

    @Id
    private String id;
    private List<MenuPlan> menuPlans;
    private Map<String, List<EnhancedIngredient>> groupedShoppingList;
    private String createdAt;
    private String createdBy;
    private String updatedAt;
    private String companyId;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MenuPlan {
        private String id;
        private String name;
        private List<Recipe> recipes;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Recipe {
        private String id;
        private String name;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EnhancedIngredient {
        private String name;
        private String unit;
        private Double totalAmount;
        private List<String> sourceRecipes;
        private String category;
        private boolean convertible;
        private Map<String, Double> processingBreakdown;
        private String totalInLargestUnit;
    }
}
