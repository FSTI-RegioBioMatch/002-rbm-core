package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.shoppinglist.ShoppingListModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ShoppingListRepository extends MongoRepository<ShoppingListModel, String> {

    List<ShoppingListModel> findByCompanyId(String companyId);

}
