package be.ordina.demo.repo;

import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    private Root<Object> meetingRoomRoot;
    @Test
    public void testCreateCriteriaQueryWithRootSelection() throws Exception {
        //given
        CriteriaFactory criteriaFactory = new CriteriaFactory();
        //when
        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Object.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Object.class)).thenReturn(meetingRoomRoot);
        //then
        assertThat(criteriaFactory.createCriteriaQueryWithRootSelection(entityManager, Object.class), IsSame.sameInstance(criteriaQuery));
        verify(criteriaQuery, atMost(1)).select(meetingRoomRoot);

    }

}