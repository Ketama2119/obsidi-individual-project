package emergenceshelter.management.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import emergenceshelter.management.user.Resident;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResidentTest {
    private Resident resident;

    @BeforeEach
    public void setUp() {
        resident = new Resident("John", "Doe", "john.doe@example.com", "1234567890", "Good", "Caseworker A");
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", resident.getFirstName(), "First name should be John");
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", resident.getLastName(), "Last name should be Doe");
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", resident.getEmail(), "Email should be john.doe@example.com");
    }

    @Test
    public void testGetPhoneNumber() {
        assertEquals("1234567890", resident.getPhoneNumber(), "Phone number should be 1234567890");
    }

    @Test
    public void testGetHealthStatus() {
        assertEquals("Good", resident.getHealthStatus(), "Health status should be Good");
    }

    @Test
    public void testGetAssignedTo() {
        assertEquals("Caseworker A", resident.getAssignedTo(), "AssignedTo should be Caseworker A");
    }
}