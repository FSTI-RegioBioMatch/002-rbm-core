package de.regiobiomatch.rbmcore.rest.services;

import de.regiobiomatch.rbmcore.rest.models.menuplan.MenuPlanModel;
import de.regiobiomatch.rbmcore.rest.models.menuplan.dtos.CreateMenuPlanDTO;
import de.regiobiomatch.rbmcore.rest.models.menuplan.subs.AvailableEnum;
import de.regiobiomatch.rbmcore.rest.repositories.MenuPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class MenuPlanService {

    @Autowired
    MenuPlanRepository menuPlanRepository;


    public MenuPlanModel createMenuPlan(CreateMenuPlanDTO createMenuPlanDTO, String currentCompany) {
        MenuPlanModel menuPlanModel = new MenuPlanModel();

        menuPlanModel.setId(UUID.randomUUID().toString());
        menuPlanModel.setCompanyId(currentCompany);
        menuPlanModel.setDescription(createMenuPlanDTO.getDescription());
        menuPlanModel.setMenuName(createMenuPlanDTO.getMenuName());
        menuPlanModel.setWeekDay(createMenuPlanDTO.getWeekDay());
        menuPlanModel.setExecutionWeekNumber(createMenuPlanDTO.getExecutionWeekNumber());
        menuPlanModel.setPlace(createMenuPlanDTO.getPlace());
        menuPlanModel.setPortions(createMenuPlanDTO.getPortions());
        menuPlanModel.setRecipes(createMenuPlanDTO.getRecipes());
        menuPlanModel.setAvailable(AvailableEnum.NOT_CHECKED);
        menuPlanModel.setMarkedForPurchase(false);
        menuPlanModel.setCreatedAt(new Date(System.currentTimeMillis()));


        return menuPlanRepository.save(menuPlanModel);
    }

    public List<MenuPlanModel> getMenuPlansByCurrentCompany(String currentCompany) {
        return menuPlanRepository.findByCompanyId(currentCompany);
    }

}
