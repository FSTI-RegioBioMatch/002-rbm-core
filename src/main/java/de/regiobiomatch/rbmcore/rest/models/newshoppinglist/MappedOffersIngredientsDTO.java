package de.regiobiomatch.rbmcore.rest.models.newshoppinglist;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MappedOffersIngredientsDTO {
    private String companyId;
    private String shoppingListId;
    private List<MappedOffersIngredientDTO> mappedOffersIngredients;

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MappedOffersIngredientDTO {
        private List<IngredientDTO> ingredient;
        private List<OfferWrapperDTO> offers;
        private String status;
        private boolean selected;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IngredientDTO {
        private String name;
        private String unit;
        private Double totalAmount;
        private List<String> sourceRecipes;
        private String category;
        private boolean convertible;
        private Map<String, Double> processingBreakdown;
        private String totalInLargestUnit;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferWrapperDTO {
        private OfferDTO offer;
        private boolean selected;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferDTO {
        private String resultType;
        private CompanyDTO company;
        private AddressDTO address;
        private List<String> roles;
        private ProductDTO product;
        private LinksDTO links;
        private OntoFoodTypeDTO ontoFoodType;
        private OfferDetailsDTO offerDetails;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompanyDTO {
        private String id;
        private String name;
        private String label;
        private boolean verified;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressDTO {
        private double lat;
        private double lon;
        private String city;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProductDTO {
        private String dateStart;
        private String dateEnd;
        private String unit;
        private int totalAmount;
        private boolean isPermanent;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinksDTO {
        private String company;
        private String offer;
        private String request;
        private String address;
        private String category;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OntoFoodTypeDTO {
        private String label;
        private boolean company;
        private boolean market;
        private SubcategoryLinksDTO links;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubcategoryLinksDTO {
        private String self;
        private List<String> supercategories;
        private List<String> subcategories;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferDetailsDTO {
        private String id;
        private String dateCreated;
        private String dateModified;
        private String dateFrom;
        private String dateEnd;
        private String description;
        private String brandName;
        private String productTitle;
        private String productType;
        private AmountDTO totalAmount;
        private AmountDTO minAmount;
        private double pricePerUnit;
        private List<GraduatedPriceDTO> graduatedPrices = new ArrayList<>(); // Initialized to empty list
        private List<LevelOfProcessingDTO> levelsOfProcessing = new ArrayList<>(); // Initialized to empty list
        private List<ContainerDTO> containers = new ArrayList<>(); // Initialized to empty list
        private Object productTraits;
        private Object caliber;
        private Object weight;
        private boolean active;
        private boolean isDeleted;
        private boolean isPermanent;
        private OfferLinksDTO links;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AmountDTO {
        private double amount;
        private String unit;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GraduatedPriceDTO {
        private double price;
        private int amount;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LevelOfProcessingDTO {
        private String label;
        private LinkDTO self;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ContainerDTO {
        private String id;
        private String containerType;
        private int amount;
        private String unit;
        private Object innerContainer;
        private Object width;
        private Object length;
        private Object height;
        private Object dimensionUnit;
        private Object material;
        private boolean returnable;
        private Object returnableNotice;
        private List<Object> traits;
        private LinkDTO links;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LinkDTO {
        private String self;
    }
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferLinksDTO {
        private String self;
        private String update;
        private String remove;
        private String company;
        private String category;
        private String contact;
        private Object latestTradeItem;
    }
}