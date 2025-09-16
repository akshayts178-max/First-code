package mobile;

import java.io.*;
import java.util.*;

public class ContactBook implements Serializable {

    Map<UUID, Contact> contacts = new HashMap<>();
    Map<String, UUID> phoneIndex = new HashMap<>();
    Map<String, Set<UUID>> nameIndex = new HashMap<>();

    public void addContact(Contact contact) throws InvalidInputException {
        if (contact == null) {
            throw new InvalidInputException("Contact cannot be null!");
        }
        contacts.put(contact.getId(), contact);
        for (String phone : contact.getPhones()) {
            phoneIndex.put(phone, contact.getId());
        }
        String nameKey = (contact.getFirstName() + " " + contact.getLastName()).toLowerCase();
        nameIndex.computeIfAbsent(nameKey, k -> new HashSet<>()).add(contact.getId());
    }

    public void updateContact(UUID id, Contact newContact) throws InvalidInputException, DuplicateContactException {
        if (id == null || newContact == null) {
            throw new InvalidInputException("Contact id or new data cannot be null!");
        }
        if (!contacts.containsKey(id)) {
            throw new InvalidInputException("Contact with id " + id + " does not exist!");
        }
        contacts.put(id, newContact);
        rebuildIndexes();
    }

    public void deleteContact(UUID id) throws InvalidInputException, ContactNotFoundException {
        if (id == null) {
            throw new InvalidInputException("Contact id cannot be null!");
        }
        if (!contacts.containsKey(id)) {
            throw new ContactNotFoundException("Contact with the id " + id + " not found!");
        }
        contacts.remove(id);
        rebuildIndexes();
    }

    public List<Contact> listAllContacts() {
        List<Contact> list = new ArrayList<>(contacts.values());
        list.sort(Comparator.comparing(Contact::getLastName).thenComparing(Contact::getFirstName));
        return list;
    }

    private void rebuildIndexes() {
        phoneIndex.clear();
        nameIndex.clear();
        for (Contact contact : contacts.values()) {
            for (String phone : contact.getPhones()) {
                phoneIndex.put(phone, contact.getId());
            }
            String nameKey = (contact.getFirstName() + " " + contact.getLastName()).toLowerCase();
            nameIndex.computeIfAbsent(nameKey, k -> new HashSet<>()).add(contact.getId());
        }
    }

    public List<Contact> searchByName(String lastName) {
        List<Contact> results = new ArrayList<>();
        for (Contact contact : contacts.values()) {
            String name = contact.getLastName();
            if (name.equalsIgnoreCase(lastName)) {
                results.add(contact);
            }
        }
        return results;
    }
}
