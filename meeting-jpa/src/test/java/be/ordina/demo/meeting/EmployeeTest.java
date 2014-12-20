package be.ordina.demo.meeting;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class EmployeeTest {

    private Employee employee;

    @Before
    public void setupEmployee() {
        employee = Employee.employee().id(1L).firstName("John").lastName("Doe").build();
    }

    @Test
    public void getId_returns_id() {
        assertThat(employee.getId(), equalTo(1L));
    }

    @Test
    public void getFirstName_returns_firstName(){
        assertThat(employee.getFirstName(), equalTo("John"));
    }

    @Test
    public void getLastName_returns_lastName(){
        assertThat(employee.getLastName(), equalTo("Doe"));
    }

}