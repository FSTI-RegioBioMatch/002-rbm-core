package de.regiobiomatch.rbmcore.rest.controllers;


import de.regiobiomatch.rbmcore.rest.models.menuplan.dtos.CreateMenuPlanDTO;
import de.regiobiomatch.rbmcore.rest.services.MenuPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/menuplan")
@CrossOrigin(
        origins = {"https://app.regiobiomatch.de", "http://localhost:4200"},
        allowedHeaders = {"Authorization", "Content-Type", "X-Requested-With", "Accept", "Origin", "Current-Company"}
)
public class MenuPlanController {


    @Autowired
    MenuPlanService menuPlanService;

    @PostMapping
    public ResponseEntity<?> createMenuPlan(@RequestHeader("Current-Company") String currentCompany, @RequestBody CreateMenuPlanDTO createMenuPlanDTO) {
        return ResponseEntity.ok(menuPlanService.createMenuPlan(createMenuPlanDTO, currentCompany));
    }

    @GetMapping
    public ResponseEntity<?> getMenuPlan(@RequestHeader("Current-Company") String currentCompany) {
        return ResponseEntity.ok(menuPlanService.getMenuPlansByCurrentCompany(currentCompany));
    }

}
