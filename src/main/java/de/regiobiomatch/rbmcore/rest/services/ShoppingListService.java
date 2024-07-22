package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.shoppinglist.ShoppingListModel;
import de.regiobiomatch.rbmcore.rest.models.shoppinglist.dtos.CreateShoppingListDTO;
import de.regiobiomatch.rbmcore.rest.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository shoppingListRepository;

    public List<ShoppingListModel> getShoppingListByCurrentCompany(String currentCompany) {
        return shoppingListRepository.findByCompanyId(currentCompany);
    }

    public ShoppingListModel createShoppingList(String currentCompany, CreateShoppingListDTO createShoppingListDTO) {
        ShoppingListModel shoppingListModel = new ShoppingListModel();
        shoppingListModel.setId(UUID.randomUUID().toString());
        shoppingListModel.setCreatedAt(new Date(System.currentTimeMillis()));
        shoppingListModel.setCompanyId(currentCompany);
        shoppingListModel.setIngredients(createShoppingListDTO.getIngredients());


        return shoppingListRepository.save(shoppingListModel);
    }

    public ShoppingListModel updateShoppingList(String currentCompany, CreateShoppingListDTO createShoppingListDTO, String id) {
        ShoppingListModel shoppingListModel = shoppingListRepository.findById(id).get();

        if (!shoppingListModel.getCompanyId().equals(currentCompany)) {
            return null;
        }

        shoppingListModel.setIngredients(createShoppingListDTO.getIngredients());

        return shoppingListRepository.save(shoppingListModel);
    }

    public ShoppingListModel getShoppingListById(String currentCompany, String id) {
        ShoppingListModel shoppingListModel = shoppingListRepository.findById(id).get();

        if (!shoppingListModel.getCompanyId().equals(currentCompany)) {
            return null;
        }

        return shoppingListModel;
    }

}
