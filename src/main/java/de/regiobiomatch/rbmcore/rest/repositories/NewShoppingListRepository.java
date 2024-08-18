package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.NewShoppingListModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NewShoppingListRepository extends MongoRepository<NewShoppingListModel, String> {

    List<NewShoppingListModel> findByCreatedBy(String createdBy);

    List<NewShoppingListModel> findByCompanyId(String companyId);
}
