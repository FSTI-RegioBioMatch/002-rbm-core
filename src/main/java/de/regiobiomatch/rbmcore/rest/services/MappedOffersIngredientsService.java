package de.regiobiomatch.rbmcore.rest.services;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsDTO;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import de.regiobiomatch.rbmcore.rest.repositories.MappedOffersIngredientsRepository;
import de.regiobiomatch.rbmcore.utils.MappedOffersIngredientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MappedOffersIngredientsService {
    private final MappedOffersIngredientsRepository repository;

    @Autowired
    public MappedOffersIngredientsService(MappedOffersIngredientsRepository repository) {
        this.repository = repository;
    }

    public MappedOffersIngredientsModel saveMappedOffersIngredients(MappedOffersIngredientsDTO dto) {
        MappedOffersIngredientsModel model = MappedOffersIngredientsMapper.toModel(dto);
        return repository.save(model);
    }

    public Optional<MappedOffersIngredientsModel> getMappedOffersIngredientsById(String id) {
        return repository.findById(id);
    }

    public Page<MappedOffersIngredientsModel> getMappedOffersIngredientsByCompanyId(String companyId, Pageable pageable) {
        return repository.findByCompanyId(companyId, pageable);
    }

    public ResponseEntity<?> deleteMappedOffersIngredientsById(String id) {
        repository.deleteById(id);
        if (repository.findById(id).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    public MappedOffersIngredientsModel updateMappedOffersIngredients(MappedOffersIngredientsDTO dto, String id) {
        MappedOffersIngredientsModel model = MappedOffersIngredientsMapper.toModel(dto);
        model.setShoppingListId(id);
        return repository.save(model);
    }
    public List<MappedOffersIngredientsModel> getMappedOffersIngredientsByShoppingListId(String shoppingListId) {
        return repository.findByShoppingListId(shoppingListId);
    }
}
