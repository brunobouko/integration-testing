package be.ordina.demo.meeting.repo;

import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CriteriaFactoryTest {

    @Mock
    private EntityManager entityManager;
    @Mock
    private CriteriaBuilder criteriaBuilder;
    @Mock
    private CriteriaQuery<Object> criteriaQuery;
    @Mock
    private CriteriaQuery<Long> criteriaQuerySearchingForLong;
    @Mock
    private Root<Object> objectRoot;
    @Mock
    private Expression<Long> countExpression;
    @Test
    public void testCreateCriteriaQueryWithRootSelection() throws Exception {
        //given
        CriteriaFactory criteriaFactory = new CriteriaFactory();
        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Object.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Object.class)).thenReturn(objectRoot);
        //then
        assertThat(criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, Object.class), IsSame.sameInstance(criteriaQuery));
        verify(criteriaQuery, atMost(1)).select(objectRoot);

    }

    @Test
    public void testCreateCountQueryWithRootSelection() throws Exception {
        //given
        CriteriaFactory criteriaFactory = new CriteriaFactory();
        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Long.class)).thenReturn(criteriaQuerySearchingForLong);
        when(criteriaQuerySearchingForLong.from(Object.class)).thenReturn(objectRoot);
        when(criteriaBuilder.count(objectRoot)).thenReturn(countExpression);
        //then
        assertThat(criteriaFactory.createCountQueryWithRootSelection(entityManager, Object.class), IsSame.sameInstance(criteriaQuerySearchingForLong));
        verify(criteriaQuery, atMost(1)).select(countExpression);

    }

}