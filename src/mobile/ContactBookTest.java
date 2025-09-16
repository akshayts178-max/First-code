package mobile;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ContactBookTest {
    UUID id = UUID.randomUUID();
    @Test
    void addContactMethodTest() {
        ContactBook contactBook = new ContactBook();
        Contact c = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        try {
            contactBook.addContact(c);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error: " + e);
        }
        assertEquals("Akshay", c.getFirstName());
        assertEquals("TS", c.getLastName());
        assertTrue(c.getPhones().contains("6366119928"));
    }

    @Test
    void updateContactMethodTest() {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        try {
            contactBook.addContact(contact);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error" + e);
        }
//        contacts.put(id, newContact);
        Contact newContact = new Contact(id, "Akshay", "Kumar", Collections.singletonList("6366119928"));
        try {
            contactBook.updateContact(id, newContact);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error" + e);
        } catch (DuplicateContactException e) {
            throw new RuntimeException("Error" + e);
        }
        assertNotNull(newContact, "Updated contact should exist");
        assertEquals("Akshay", newContact.getFirstName());
        assertEquals("Kumar", newContact.getLastName());
        assertTrue(newContact.getPhones().contains("6366119928"));
    }

    @Test
    void deleteContactMethodTest() {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        try {
            contactBook.addContact(contact);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error" + e);
        }
        try {
            contactBook.deleteContact(id);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error" + e);
        } catch (ContactNotFoundException e) {
            throw new RuntimeException("Error" + e);
        }
        assertTrue(contactBook.listAllContacts().isEmpty());
    }

    @Test
    void listAllContactMethodTest() {
        ContactBook contactBook = new ContactBook();
        Contact contact = new Contact(id, "Akshay", "TS", Collections.singletonList("6366119928"));
        try {
            contactBook.addContact(contact);
        } catch (InvalidInputException e) {
            throw new RuntimeException("Error" + e);
        }
        List<Contact> list = contactBook.listAllContacts();
        assertNotNull(list);
    }
}