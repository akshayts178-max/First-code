package mobile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTest {
    @Test
    void testContactCreation() {
        Contact contact = new Contact("Akshay", "TS");
        assertEquals("Akshay", contact.getFirstName());
        assertEquals("TS", contact.getLastName());
    }
}