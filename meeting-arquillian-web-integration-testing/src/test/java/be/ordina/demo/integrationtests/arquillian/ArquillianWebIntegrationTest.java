package be.ordina.demo.integrationtests.arquillian;

import be.ordina.demo.meeting.MeetingRoom;
import be.ordina.demo.meeting.repo.MeetingRoomRepository;
import be.ordina.demo.meeting.rest.MeetingOrganizerRestService;
import be.ordina.demo.meeting.service.MeetingOrganizer;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.angular.findby.FindByNg;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Arquillian.class)
@RunAsClient
public class ArquillianWebIntegrationTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.createFromZipFile(WebArchive.class, new File("../meeting-web/target/meeting-web.war"))
                .addPackage(MeetingOrganizerRestService.class.getPackage())
                .addPackage(MeetingRoom.class.getPackage())
                .addPackage(MeetingOrganizer.class.getPackage())
                .addPackage(MeetingRoomRepository.class.getPackage())
                .addPackage(Function.class.getPackage())
                .addPackage(FluentIterable.class.getPackage())
                .addAsResource("persistence-test.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @FindBy(tagName="body")
    private WebElement restResponse;

    @FindBy(tagName = "h1")
    private WebElement aliveCheck;

    @FindBy(tagName = "h2")
    private WebElement selectedMeetingRoom;

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    @FindByNg(repeat = "meetingRoom in meetingRooms")
    private List<WebElement> meetingRooms;

    @Test
    public void should_be_alive() {
        browser.get(deploymentUrl.toExternalForm() + "meeting/organizer/alive");

        assertThat(restResponse.getText()).isEqualTo("{\"text\":\"alive\"}");
    }

    @Test
    public void should_have_4_meeting_room_at_rest_url() {

        browser.get(deploymentUrl.toExternalForm() + "meeting/organizer/meetingrooms");

        assertThat(restResponse.getText()).isEqualTo(   "[{\"id\":\"1\",\"name\":\"Europa\",\"capacity\":10}," +
                                                        "{\"id\":\"2\",\"name\":\"Antwerp\",\"capacity\":15}," +
                                                        "{\"id\":\"3\",\"name\":\"Ghent\",\"capacity\":5}," +
                                                        "{\"id\":\"4\",\"name\":\"Liege\",\"capacity\":6}]");
    }

    @Test
    public void should_read_the_meeting_organizer_app_is_alive_when_loading_index_html() {
        browser.get(deploymentUrl.toExternalForm() + "app/index.html");

        assertThat(aliveCheck.getText()).isEqualTo("The meeting organizer app is alive!");
    }

    @Test
    public void should_have_a_selection_of_existing_meetingRooms_when_loading_index_html() {
        browser.get(deploymentUrl.toExternalForm() + "app/index.html");

        assertThat(meetingRooms.size()).isEqualTo(4);
    }

    @Test
    public void should_set_text_of_paragraph_to_selected_meetingRoom() {
        browser.get(deploymentUrl.toExternalForm() + "app/index.html");

        WebElement webElement = meetingRooms.get(2);
        webElement.click();

        assertThat(selectedMeetingRoom.getText()).isEqualTo("The selected meeting-room is Ghent");
    }

}
