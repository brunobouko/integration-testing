package be.ordina.demo.meeting;

import static be.ordina.demo.meeting.Employee.employee;

public class EmployeeMother {
    public static Employee getJohDoe() {
        return employee().firstName("Joh").lastName("Doe").build();
    }
    public static Employee getHomerSimpson() {
        return employee().firstName("Homer").lastName("Simpson").build();
    }
}
