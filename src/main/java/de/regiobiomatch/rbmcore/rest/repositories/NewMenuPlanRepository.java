package de.regiobiomatch.rbmcore.rest.repositories;

import de.regiobiomatch.rbmcore.rest.models.newmenuplan.NewMenuPlan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NewMenuPlanRepository extends MongoRepository<NewMenuPlan, String> {
    Optional<NewMenuPlan> findByIdAndCompanyId(String id, String companyId);
    List<NewMenuPlan> findAllByCompanyId(String companyId);
}