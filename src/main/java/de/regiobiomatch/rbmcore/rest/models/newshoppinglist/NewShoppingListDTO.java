package de.regiobiomatch.rbmcore.rest.models.newshoppinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewShoppingListDTO {
    private List<NewShoppingListModel.MenuPlan> menuPlans;
    private Map<String, List<NewShoppingListModel.EnhancedIngredient>> groupedShoppingList;
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
        private List<NewShoppingListModel.Recipe> recipes;
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
