package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsDTO;
import de.regiobiomatch.rbmcore.rest.models.newshoppinglist.MappedOffersIngredientsModel;
import de.regiobiomatch.rbmcore.rest.repositories.MappedOffersIngredientsRepository;
import de.regiobiomatch.rbmcore.utils.MappedOffersIngredientsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    public Optional<MappedOffersIngredientsModel> getMappedOffersIngredientsById(String id, String companyId) {
        return repository.findByIdAndCompanyId(id, companyId);
    }

    public Page<MappedOffersIngredientsModel> getMappedOffersIngredientsByCompanyId(String companyId, Pageable pageable) {
        return repository.findByCompanyId(companyId, pageable);
    }

    public ResponseEntity<?> deleteMappedOffersIngredientsById(String id, String companyId) {
        Optional<MappedOffersIngredientsModel> modelOptional = repository.findByIdAndCompanyId(id, companyId);
        if (modelOptional.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public Optional<MappedOffersIngredientsModel> updateMappedOffersIngredients(MappedOffersIngredientsDTO dto, String id, String companyId) {
        Optional<MappedOffersIngredientsModel> existingModel = repository.findByIdAndCompanyId(id, companyId);
        if (existingModel.isPresent()) {
            MappedOffersIngredientsModel model = MappedOffersIngredientsMapper.toModel(dto);
            model.setId(id); // Ensure the ID remains the same
            model.setCompanyId(companyId); // Ensure the company ID remains the same
            MappedOffersIngredientsModel updatedModel = repository.save(model);
            return Optional.of(updatedModel);
        } else {
            return Optional.empty();
        }
    }

    public List<MappedOffersIngredientsModel> getMappedOffersIngredientsByShoppingListIdAndCompanyId(String shoppingListId, String companyId) {
        return repository.findByShoppingListIdAndCompanyId(shoppingListId, companyId);
    }
}
