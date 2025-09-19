package mobile;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ContactBookTest {
    UUID id = UUID.randomUUID();
    @Test
    void addContactMethodTest() throws InvalidInputException {
        ContactBook contactBook = new ContactBook();
        Contact c = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        contactBook.addContact(c, id);
        List<Contact> found = contactBook.searchByName("TS");
        assertEquals(1, found.size());
        assertEquals("TS", found.getFirst().getLastName());
        assertEquals("Akshay", found.getFirst().getFirstName());
    }

    @Test
    void updateContactMethodTest() throws InvalidInputException, DuplicateContactException {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        contactBook.addContact(contact, id);
        Contact newContact = new Contact(id, "Akshay", "Kumar", Collections.singletonList("6366119928"));
        contactBook.updateContact(id, newContact);
        List<Contact> found = contactBook.searchByName("Kumar");
        assertNotNull(found, "Updated contact should exist");
        assertEquals(1, found.size());
        assertEquals("Akshay", found.getFirst().getFirstName());
        assertEquals("Kumar", found.getFirst().getLastName());
    }

    @Test
    void deleteContactMethodTest() throws InvalidInputException, ContactNotFoundException {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        contactBook.addContact(contact, id);
        contactBook.deleteContact(id);
        assertTrue(contactBook.listAllContacts().isEmpty());
    }

    @Test
    void listAllContactMethodTest() throws InvalidInputException {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        contactBook.addContact(contact, id);
        List<Contact> list = contactBook.listAllContacts();
        assertNotNull(list);
    }
}