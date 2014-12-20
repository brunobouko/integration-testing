package be.ordina.demo.repo;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
@Component
public class CriteriaFactory {
    public <T> CriteriaQuery<T> createCriteriaQueryWithRootSelection(EntityManager entityManager, Class<T> rootClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> roomCriteriaQuery = criteriaBuilder.createQuery(rootClass);
        Root<T> root = roomCriteriaQuery.from(rootClass);
        roomCriteriaQuery.select(root);
        return roomCriteriaQuery;
    }
}
