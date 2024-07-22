package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.menuplan.MenuPlanModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MenuPlanRepository extends MongoRepository<MenuPlanModel, String> {
    List<MenuPlanModel> findByCompanyId(String companyId);

}
