package be.ordina.demo.meeting.repo;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
@Named
public class CriteriaFactory {
    public <T> CriteriaQuery<T> createCriteriaQueryWithRootSelection(EntityManager entityManager, Class<T> rootClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(rootClass);
        Root<T> root = query.from(rootClass);
        query.select(root);
        return query;
    }

    public <T> CriteriaQuery<Long> createCountQueryWithRootSelection(EntityManager entityManager, Class<T> rootClass) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<T> root = query.from(rootClass);
        Expression<Long> countExpression = criteriaBuilder.count(root);
        query.select(countExpression);
        return query;
    }
}
