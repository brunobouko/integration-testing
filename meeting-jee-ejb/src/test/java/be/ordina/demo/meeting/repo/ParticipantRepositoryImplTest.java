package be.ordina.demo.meeting.repo;

import be.ordina.demo.meeting.Participant;
import be.ordina.demo.meeting.ParticipantMother;
import org.hamcrest.core.IsSame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class ParticipantRepositoryImplTest {

    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private ParticipantRepositoryImpl employeeRepository = new ParticipantRepositoryImpl();

    private Participant participant = ParticipantMother.getHomerSimpson();

    @Test
    public void create_calls_persist_on_EntityManager_and_returns_Employee() {
        Participant createdParticipant = employeeRepository.create(participant);
        assertThat(createdParticipant, IsSame.sameInstance(participant));
        verify(entityManager, times(1)).persist(participant);
    }

}