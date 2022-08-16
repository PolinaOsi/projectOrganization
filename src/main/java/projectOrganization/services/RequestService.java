package projectOrganization.services;

import projectOrganization.dto.Request8DTO;
import projectOrganization.entity.*;
import projectOrganization.entity.Military_units_;
import projectOrganization.exceptions.BadRequest;
import projectOrganization.repository.*;
import projectOrganization.dto.Request12DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    Military_unitsRepository military_unitsRepository;
    @Autowired
    ArmamentsRepository armamentsRepository;
    @Autowired
    TechnicsRepository technicsRepository;

    @PersistenceContext
    EntityManager entityManager;

    public List<Military_units> getMilitary_unitByFilter12(Request12DTO request) throws BadRequest {
        final BigDecimal count_arm = BigDecimal.valueOf(10);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Military_units> cq = cb.createQuery(Military_units.class);

        Root<Military_units> military_unitsRoot = cq.from(Military_units.class);
        ListJoin<Military_units, Armaments> Military_unitArmamentJoin = military_unitsRoot.join(Military_units_.armaments);
        Predicate first = cb.and();
        if (request.getCategory() != null) {
            if (!armamentsRepository.findByCategory(request.getCategory()).isEmpty()) {
                first = cb.equal(Military_unitArmamentJoin.get("category"), request.getCategory());
            }
        }
        cq.select(military_unitsRoot)
                .where(first)
                .groupBy(military_unitsRoot.get("id_unit"))
                .having(cb.greaterThan(cb.sum(Military_unitArmamentJoin.get("count_armament")), count_arm));
        TypedQuery<Military_units> res = entityManager.createQuery(cq);
        return res.getResultList();
    }

    public List<Military_units> getMilitary_unitByFilter8(Request8DTO request) throws BadRequest {
        final BigDecimal count_tec = BigDecimal.valueOf(5);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Military_units> cq = cb.createQuery(Military_units.class);

        Root<Military_units> military_unitsRoot = cq.from(Military_units.class);
        ListJoin<Military_units, Technics> Military_unitTechnicJoin = military_unitsRoot.join(Military_units_.technics);
        Predicate first = cb.and();
        if (request.getCategory() != null) {
            if (!technicsRepository.findByCategory(request.getCategory()).isEmpty()) {
                first = cb.equal(Military_unitTechnicJoin.get("category"), request.getCategory());
            }
        }
        cq.select(military_unitsRoot)
                .where(first)
                .groupBy(military_unitsRoot.get("id_unit"))
                .having(cb.greaterThan(cb.sum(Military_unitTechnicJoin.get("count_technic")), count_tec));
        TypedQuery<Military_units> res = entityManager.createQuery(cq);
        return res.getResultList();
    }
}