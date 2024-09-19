package de.regiobiomatch.rbmcore.rest.models.newshoppinglist;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "mapped_offers_ingredients")
public class MappedOffersIngredientsModel {

    @Id
    private String id;
    private String companyId;
    private String shoppingListId;
    private List<MappedOffersIngredient> mappedOffersIngredients;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MappedOffersIngredient {
        private List<Ingredient> ingredient;
        private List<OfferWrapper> offers;
        private String status;
        private boolean selected;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Ingredient {
        private String name;
        private String unit;
        private Double totalAmount;
        private List<String> sourceRecipes;
        private String category;
        private boolean convertible;
        private Map<String, Double> processingBreakdown;
        private String totalInLargestUnit;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferWrapper {
        private Offer offer;
        private boolean selected;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Offer {
        private String resultType;
        private Company company;
        private Address address;
        private List<String> roles;
        private Product product;
        private Links links;
        private OntoFoodType ontoFoodType;
        private OfferDetails offerDetails;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Company {
        private String id;
        private String name;
        private String label;
        private boolean verified;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        private double lat;
        private double lon;
        private String city;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String dateStart;
        private String dateEnd;
        private String unit;
        private int totalAmount;
        private boolean isPermanent;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Links {
        private String company;
        private String offer;
        private String request;
        private String address;
        private String category;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OntoFoodType {
        private String label;
        private boolean company;
        private boolean market;
        private SubcategoryLinks links;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SubcategoryLinks {
        private String self;
        private List<String> supercategories;
        private List<String> subcategories;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferDetails {
        private String id;
        private String dateCreated;
        private String dateModified;
        private String dateFrom;
        private String dateEnd;
        private String description;
        private String brandName;
        private String productTitle;
        private String productType;
        private Amount totalAmount;
        private Amount minAmount;
        private double pricePerUnit;
        private List<GraduatedPrice> graduatedPrices = new ArrayList<>(); // Initialized to empty list
        private List<LevelOfProcessing> levelsOfProcessing = new ArrayList<>(); // Initialized to empty list
        private List<Container> containers = new ArrayList<>(); // Initialized to empty list
        private Object productTraits;
        private Object caliber;
        private Object weight;
        private boolean active;
        private boolean isDeleted;
        private boolean isPermanent;
        private OfferLinks links;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amount {
        private double amount;
        private String unit;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GraduatedPrice {
        private double price;
        private int amount;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LevelOfProcessing {
        private String label;
        private Link self;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Container {
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
        private Link links;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Link {
        private String self;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferLinks {
        private String self;
        private String update;
        private String remove;
        private String company;
        private String category;
        private String contact;
        private Object latestTradeItem;
    }
}