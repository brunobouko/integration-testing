package be.ordina.demo.meeting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class ParticipantTest {

    private Participant participant;

    @Before
    public void setupEmployee() {
        participant = Participant.employee().id(1L).firstName("John").lastName("Doe").build();
    }

    @Test
    public void getId_returns_id() {
        assertThat(participant.getId(), equalTo(1L));
    }

    @Test
    public void getFirstName_returns_firstName(){
        assertThat(participant.getFirstName(), equalTo("John"));
    }

    @Test
    public void getLastName_returns_lastName(){
        assertThat(participant.getLastName(), equalTo("Doe"));
    }

}