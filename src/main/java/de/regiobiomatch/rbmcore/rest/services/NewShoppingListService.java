package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.NewShoppingListModel;
import de.regiobiomatch.rbmcore.rest.repositories.NewShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewShoppingListService {

    private final NewShoppingListRepository newShoppingListRepository;

    @Autowired
    public NewShoppingListService(NewShoppingListRepository newShoppingListRepository) {
        this.newShoppingListRepository = newShoppingListRepository;
    }

    public NewShoppingListModel createShoppingList(NewShoppingListModel shoppingListModel) {
        shoppingListModel.setId(UUID.randomUUID().toString());
        return newShoppingListRepository.save(shoppingListModel);
    }

    public ResponseEntity<NewShoppingListModel> getShoppingListByIdAndCompanyId(String id, String companyId) {
        Optional<NewShoppingListModel> optionalShoppingList = newShoppingListRepository.findById(id);

        if (optionalShoppingList.isPresent()) {
            NewShoppingListModel shoppingList = optionalShoppingList.get();
            System.out.println("Retrieved ShoppingList: " + shoppingList);
            System.out.println("ShoppingList CompanyId: " + shoppingList.getCompanyId());
            if (shoppingList.getCompanyId().equals(companyId)) {
                return ResponseEntity.ok(shoppingList);
            }
        }
        return ResponseEntity.notFound().build();
    }


    public List<NewShoppingListModel> getAllShoppingListsByCompanyId(String companyId) {
        return newShoppingListRepository.findByCompanyId(companyId);
    }

    public ResponseEntity<?> deleteShoppingListByIdAndCompanyId(String id, String companyId) {
        Optional<NewShoppingListModel> optionalShoppingList = newShoppingListRepository.findById(id);
        if (optionalShoppingList.isPresent() && optionalShoppingList.get().getCreatedBy().equals(companyId)) {
            newShoppingListRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> updateShoppingList(String id, NewShoppingListModel updatedShoppingList, String companyId) {
        Optional<NewShoppingListModel> optionalShoppingList = newShoppingListRepository.findById(id);
        if (optionalShoppingList.isPresent() && optionalShoppingList.get().getCreatedBy().equals(companyId)) {
            updatedShoppingList.setId(id); // ensure the ID remains the same
            NewShoppingListModel savedShoppingList = newShoppingListRepository.save(updatedShoppingList);
            return ResponseEntity.ok(savedShoppingList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
