package de.regiobiomatch.rbmcore.rest.models.newshoppinglist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "shopping_list_track")
public class ShoppingListOffersPurchaseIntendPriceRequestTrackModel {

    @Id
    private String id;
    private String shoppingListId;
    private List<String> purchaseIntedIds;
    private List<String> priceRequestIds;
    private List<String> orderIds;
}
