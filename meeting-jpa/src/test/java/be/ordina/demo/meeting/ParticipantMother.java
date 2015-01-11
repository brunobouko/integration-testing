package be.ordina.demo.meeting;

import java.util.Arrays;
import java.util.List;

import static be.ordina.demo.meeting.Participant.employee;

public class ParticipantMother {
    public static Participant getJohDoe() {
        return employee().firstName("Joh").lastName("Doe").build();
    }

    public static List<Participant> getTheSimpsons() {
        return Arrays.asList(getHomerSimpson(), getMargeBouvier(), getBartSimpson(), getLisaSimpson(), getMaggySimpson());
    }
    public static Participant getHomerSimpson() {
        return employee().firstName("Homer").lastName("Simpson").build();
    }
    public static Participant getMargeBouvier() {
        return employee().firstName("Marge").lastName("Bouvier").build();
    }
    public static Participant getBartSimpson() {
        return employee().firstName("Bart").lastName("Simpson").build();
    }
    public static Participant getLisaSimpson() {
        return employee().firstName("Lisa").lastName("Simpson").build();
    }
    public static Participant getMaggySimpson() {
        return employee().firstName("Maggy").lastName("Simpson").build();
    }
}
