package de.regiobiomatch.rbmcore.utils;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsDTO;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MappedOffersIngredientsMapper {

    // Utility class should not be instantiated
    private MappedOffersIngredientsMapper() {}

    // Convert DTO to Model
    public static MappedOffersIngredientsModel toModel(MappedOffersIngredientsDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel model = new MappedOffersIngredientsModel();
        model.setShoppingListId(dto.getShoppingListId());
        model.setCompanyId(dto.getCompanyId());
        model.setMappedOffersIngredients(
                safeList(dto.getMappedOffersIngredients())
                        .stream()
                        .map(MappedOffersIngredientsMapper::toModel)
                        .collect(Collectors.toList())
        );

        return model;
    }

    // Nested conversion for MappedOffersIngredient
    public static MappedOffersIngredientsModel.MappedOffersIngredient toModel(MappedOffersIngredientsDTO.MappedOffersIngredientDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.MappedOffersIngredient model = new MappedOffersIngredientsModel.MappedOffersIngredient();
        model.setIngredient(
                safeList(dto.getIngredient())
                        .stream()
                        .map(MappedOffersIngredientsMapper::toModel)
                        .collect(Collectors.toList())
        );
        model.setOffers(
                safeList(dto.getOffers())
                        .stream()
                        .map(MappedOffersIngredientsMapper::toModel)
                        .collect(Collectors.toList())
        );
        model.setStatus(dto.getStatus());
        model.setSelected(dto.isSelected());

        return model;
    }

    // Nested conversion for Ingredient
    public static MappedOffersIngredientsModel.Ingredient toModel(MappedOffersIngredientsDTO.IngredientDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Ingredient model = new MappedOffersIngredientsModel.Ingredient();
        model.setName(dto.getName());
        model.setUnit(dto.getUnit());
        model.setTotalAmount(dto.getTotalAmount());
        model.setSourceRecipes(safeList(dto.getSourceRecipes()));
        model.setCategory(dto.getCategory());
        model.setConvertible(dto.isConvertible());
        model.setProcessingBreakdown(dto.getProcessingBreakdown());
        model.setTotalInLargestUnit(dto.getTotalInLargestUnit());

        return model;
    }

    // Nested conversion for OfferWrapper
    public static MappedOffersIngredientsModel.OfferWrapper toModel(MappedOffersIngredientsDTO.OfferWrapperDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.OfferWrapper model = new MappedOffersIngredientsModel.OfferWrapper();
        model.setOffer(toModel(dto.getOffer()));
        model.setSelected(dto.isSelected());

        return model;
    }

    // Nested conversion for Offer
    public static MappedOffersIngredientsModel.Offer toModel(MappedOffersIngredientsDTO.OfferDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Offer model = new MappedOffersIngredientsModel.Offer();
        model.setResultType(dto.getResultType());
        model.setCompany(toModel(dto.getCompany()));
        model.setAddress(toModel(dto.getAddress()));
        model.setRoles(safeList(dto.getRoles()));
        model.setProduct(toModel(dto.getProduct()));
        model.setLinks(toModel(dto.getLinks()));
        model.setOntoFoodType(toModel(dto.getOntoFoodType()));
        model.setOfferDetails(toModel(dto.getOfferDetails()));

        return model;
    }

    // Convert AddressDTO to Address Model
    public static MappedOffersIngredientsModel.Address toModel(MappedOffersIngredientsDTO.AddressDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Address model = new MappedOffersIngredientsModel.Address();
        model.setLat(dto.getLat());
        model.setLon(dto.getLon());
        model.setCity(dto.getCity());

        return model;
    }

    // Convert CompanyDTO to Company Model
    public static MappedOffersIngredientsModel.Company toModel(MappedOffersIngredientsDTO.CompanyDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Company model = new MappedOffersIngredientsModel.Company();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setLabel(dto.getLabel());
        model.setVerified(dto.isVerified());

        return model;
    }

    // Convert ProductDTO to Product Model
    public static MappedOffersIngredientsModel.Product toModel(MappedOffersIngredientsDTO.ProductDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Product model = new MappedOffersIngredientsModel.Product();
        model.setDateStart(dto.getDateStart());
        model.setDateEnd(dto.getDateEnd());
        model.setUnit(dto.getUnit());
        model.setTotalAmount(dto.getTotalAmount());
        model.setPermanent(dto.isPermanent());

        return model;
    }

    // Convert OntoFoodTypeDTO to OntoFoodType Model
    public static MappedOffersIngredientsModel.OntoFoodType toModel(MappedOffersIngredientsDTO.OntoFoodTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.OntoFoodType model = new MappedOffersIngredientsModel.OntoFoodType();
        model.setLabel(dto.getLabel());
        model.setCompany(dto.isCompany());
        model.setMarket(dto.isMarket());
        model.setLinks(toModel(dto.getLinks())); // Conversion method for SubcategoryLinksDTO

        return model;
    }

    // Convert LinksDTO to Links Model
    public static MappedOffersIngredientsModel.Links toModel(MappedOffersIngredientsDTO.LinksDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Links model = new MappedOffersIngredientsModel.Links();
        model.setCompany(dto.getCompany());
        model.setOffer(dto.getOffer());
        model.setRequest(dto.getRequest());
        model.setAddress(dto.getAddress());
        model.setCategory(dto.getCategory());

        return model;
    }

    // Convert OfferDetailsDTO to OfferDetails Model
    public static MappedOffersIngredientsModel.OfferDetails toModel(MappedOffersIngredientsDTO.OfferDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.OfferDetails model = new MappedOffersIngredientsModel.OfferDetails();
        model.setId(dto.getId());
        model.setDateCreated(dto.getDateCreated());
        model.setDateModified(dto.getDateModified());
        model.setDateFrom(dto.getDateFrom());
        model.setDateEnd(dto.getDateEnd());
        model.setDescription(dto.getDescription());
        model.setBrandName(dto.getBrandName());
        model.setProductTitle(dto.getProductTitle());
        model.setProductType(dto.getProductType());
        model.setTotalAmount(toModel(dto.getTotalAmount())); // Conversion method for AmountDTO
        model.setMinAmount(toModel(dto.getMinAmount()));     // Conversion method for AmountDTO
        model.setPricePerUnit(dto.getPricePerUnit());
        model.setGraduatedPrices(safeList(dto.getGraduatedPrices())
                .stream()
                .map(MappedOffersIngredientsMapper::toModel)
                .collect(Collectors.toList())); // Conversion for GraduatedPriceDTO
        model.setLevelsOfProcessing(safeList(dto.getLevelsOfProcessing())
                .stream()
                .map(MappedOffersIngredientsMapper::toModel)
                .collect(Collectors.toList())); // Conversion for LevelOfProcessingDTO
        model.setContainers(safeList(dto.getContainers())
                .stream()
                .map(MappedOffersIngredientsMapper::toModel)
                .collect(Collectors.toList())); // Conversion for ContainerDTO
        model.setProductTraits(dto.getProductTraits());
        model.setCaliber(dto.getCaliber());
        model.setWeight(dto.getWeight());
        model.setActive(dto.isActive());
        model.setDeleted(dto.isDeleted());
        model.setPermanent(dto.isPermanent());
        model.setLinks(toModel(dto.getLinks())); // Conversion method for OfferLinksDTO

        return model;
    }

    // Convert SubcategoryLinksDTO to SubcategoryLinks Model
    public static MappedOffersIngredientsModel.SubcategoryLinks toModel(MappedOffersIngredientsDTO.SubcategoryLinksDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.SubcategoryLinks model = new MappedOffersIngredientsModel.SubcategoryLinks();
        model.setSelf(dto.getSelf());
        model.setSupercategories(safeList(dto.getSupercategories()));
        model.setSubcategories(safeList(dto.getSubcategories()));

        return model;
    }

    // Convert AmountDTO to Amount Model
    public static MappedOffersIngredientsModel.Amount toModel(MappedOffersIngredientsDTO.AmountDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Amount model = new MappedOffersIngredientsModel.Amount();
        model.setAmount(dto.getAmount());
        model.setUnit(dto.getUnit());

        return model;
    }

    // Convert GraduatedPriceDTO to GraduatedPrice Model
    public static MappedOffersIngredientsModel.GraduatedPrice toModel(MappedOffersIngredientsDTO.GraduatedPriceDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.GraduatedPrice model = new MappedOffersIngredientsModel.GraduatedPrice();
        model.setPrice(dto.getPrice());
        model.setAmount(dto.getAmount());

        return model;
    }

    // Convert LevelOfProcessingDTO to LevelOfProcessing Model
    public static MappedOffersIngredientsModel.LevelOfProcessing toModel(MappedOffersIngredientsDTO.LevelOfProcessingDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.LevelOfProcessing model = new MappedOffersIngredientsModel.LevelOfProcessing();
        model.setLabel(dto.getLabel());
        model.setSelf(toModel(dto.getSelf())); // Conversion method for LinkDTO

        return model;
    }

    // Convert ContainerDTO to Container Model
    public static MappedOffersIngredientsModel.Container toModel(MappedOffersIngredientsDTO.ContainerDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Container model = new MappedOffersIngredientsModel.Container();
        model.setId(dto.getId());
        model.setContainerType(dto.getContainerType());
        model.setAmount(dto.getAmount());
        model.setUnit(dto.getUnit());
        model.setInnerContainer(dto.getInnerContainer());
        model.setWidth(dto.getWidth());
        model.setLength(dto.getLength());
        model.setHeight(dto.getHeight());
        model.setDimensionUnit(dto.getDimensionUnit());
        model.setMaterial(dto.getMaterial());
        model.setReturnable(dto.isReturnable());
        model.setReturnableNotice(dto.getReturnableNotice());
        model.setTraits(safeList(dto.getTraits()));
        model.setLinks(toModel(dto.getLinks())); // Conversion method for LinkDTO

        return model;
    }

    // Convert LinkDTO to Link Model
    public static MappedOffersIngredientsModel.Link toModel(MappedOffersIngredientsDTO.LinkDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.Link model = new MappedOffersIngredientsModel.Link();
        model.setSelf(dto.getSelf());

        return model;
    }

    // Convert OfferLinksDTO to OfferLinks Model
    public static MappedOffersIngredientsModel.OfferLinks toModel(MappedOffersIngredientsDTO.OfferLinksDTO dto) {
        if (dto == null) {
            return null;
        }

        MappedOffersIngredientsModel.OfferLinks model = new MappedOffersIngredientsModel.OfferLinks();
        model.setSelf(dto.getSelf());
        model.setUpdate(dto.getUpdate());
        model.setRemove(dto.getRemove());
        model.setCompany(dto.getCompany());
        model.setCategory(dto.getCategory());
        model.setContact(dto.getContact());
        model.setLatestTradeItem(dto.getLatestTradeItem());

        return model;
    }

    // Helper method to safely handle potential null lists
    private static <T> List<T> safeList(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }
}
