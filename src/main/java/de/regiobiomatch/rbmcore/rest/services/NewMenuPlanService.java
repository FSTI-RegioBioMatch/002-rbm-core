package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.newmenuplan.NewMenuPlan;
import de.regiobiomatch.rbmcore.rest.models.newmenuplan.NewMenuPlanDTO;
import de.regiobiomatch.rbmcore.rest.repositories.NewMenuPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NewMenuPlanService {

    private final NewMenuPlanRepository newMenuPlanRepository;

    @Autowired
    public NewMenuPlanService(NewMenuPlanRepository newMenuPlanRepository) {
        this.newMenuPlanRepository = newMenuPlanRepository;
    }

    public NewMenuPlan saveMenuPlan(NewMenuPlanDTO menuPlanDto) {
        NewMenuPlan menuPlan = convertToEntity(menuPlanDto);
        return newMenuPlanRepository.save(menuPlan);
    }

    public ResponseEntity<NewMenuPlan> getMenuPlanByIdAndCompanyId(String id, String companyId) {
        Optional<NewMenuPlan> optionalMenuPlan = newMenuPlanRepository.findByIdAndCompanyId(id, companyId);
        return optionalMenuPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public List<NewMenuPlan> getAllMenuPlansByCompanyId(String companyId) {
        return newMenuPlanRepository.findAllByCompanyId(companyId);
    }

    public ResponseEntity<?> deleteMenuPlanByIdAndCompanyId(String id, String companyId) {
        Optional<NewMenuPlan> optionalMenuPlan = newMenuPlanRepository.findByIdAndCompanyId(id, companyId);
        if (optionalMenuPlan.isPresent()) {
            newMenuPlanRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<?> deleteEventFromMenuPlan(String menuPlanId, String eventId, String companyId) {
        Optional<NewMenuPlan> optionalMenuPlan = newMenuPlanRepository.findByIdAndCompanyId(menuPlanId, companyId);
        if (optionalMenuPlan.isPresent()) {
            NewMenuPlan menuPlan = optionalMenuPlan.get();
            // Remove the specific event
            menuPlan.getEvents().removeIf(event -> event.getId().equals(eventId));

            // Check if any events are left
            if (menuPlan.getEvents().isEmpty()) {
                // If no events are left, delete the entire menu plan
                newMenuPlanRepository.deleteById(menuPlanId);
                return ResponseEntity.noContent().build();
            } else {
                // Otherwise, save the updated menu plan and return it
                NewMenuPlan updatedMenuPlan = newMenuPlanRepository.save(menuPlan);
                return ResponseEntity.ok(updatedMenuPlan);
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private NewMenuPlan convertToEntity(NewMenuPlanDTO dto) {
        NewMenuPlan menuPlan = new NewMenuPlan();
        menuPlan.setId(dto.getId() != null ? dto.getId() : UUID.randomUUID().toString());
        menuPlan.setName(dto.getName());
        menuPlan.setNextExecution(dto.getNextExecution());
        menuPlan.setWeekday(dto.getWeekday());
        menuPlan.setRepeatFrequency(dto.getRepeatFrequency());
        menuPlan.setLocation(dto.getLocation());
        menuPlan.setPortions(dto.getPortions());
        menuPlan.setPortionsVegetarisch(dto.getPortionsVegetarisch());
        menuPlan.setPortionsVegan(dto.getPortionsVegan());
        menuPlan.setDescription(dto.getDescription());
        menuPlan.setRecipes(dto.getRecipes());
        menuPlan.setEvents(dto.getEvents());
        menuPlan.setCompanyId(dto.getCompanyId());  // Make sure to set the companyId
        return menuPlan;
    }
}