package projectOrganization.services;

import projectOrganization.entity.*;
import projectOrganization.entity.Military_units_;
import projectOrganization.exceptions.BadRequest;
import projectOrganization.repository.*;
import projectOrganization.dto.Request1DTO;
import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    Military_unitsRepository military_unitsRepository;
    @Autowired
    ArmamentsRepository armamentsRepository;

    @PersistenceContext
    EntityManager entityManager;

    public List<Military_units> getMilitary_unitByFilter(Request1DTO request) throws BadRequest {
        final Integer count_arm = 20;

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Military_units> cq = cb.createQuery(Military_units.class);
        CriteriaQuery<Armaments> cqA = cb.createQuery(Armaments.class);

        Root<Military_units> military_unitsRoot = cq.from(Military_units.class);
        Root<Armaments> armamentsRoot = cq.from(Armaments.class);
        Join<Military_units, Armaments> Military_unitArmamentJoin = military_unitsRoot.join(Military_units_.armaments);

//        System.out.println(request.getCategoryId());
//        System.out.println(request.getManufactureId());
//        System.out.println(request.getDepartmentId());
        //  = cb.and() for always true
        Predicate first = cb.and();
        Predicate second = cb.and();
        if (request.getCategory() != null) {
            if (!armamentsRepository.findByCategory(request.getCategory()).isEmpty()) {
//                cqA.select(armamentsRoot);
                first = cb.equal(Military_unitArmamentJoin.get("category"), request.getCategory());
            }
        }

        cq.select(military_unitsRoot)
                .where(first)
                .groupBy(military_unitsRoot.get("id_unit"))
                .having(cb.greaterThan(cb.sum(Military_unitArmamentJoin.get("count_armament")), count_arm));
        TypedQuery<Military_units> res = entityManager.createQuery(cq);
//        System.out.println(request.getCategory());
//        System.out.println(res.getResultList().size());
//        System.out.println(armamentsRepository.findByCategory(request.getCategory()).size());
        return res.getResultList();
    }
}