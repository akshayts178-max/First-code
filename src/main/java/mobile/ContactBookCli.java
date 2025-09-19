package mobile;

import java.util.*;

public class ContactBookCli {
    private static final Scanner sc = new Scanner(System.in);
    private static final ContactBook contactBook = new ContactBook();

    public void run(String[] args) {
        while (true) {
            System.out.println("\n--- Contacts ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Update Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. List All Contact");
            System.out.println("5. Exit");
            System.out.println("Choose: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> addContactFlow();
                case 2 -> updateContactFlow();
                case 3 -> deleteContactFlow();
                case 4 -> listAllContactFlow();
                case 5 -> {
                    System.out.println("Exiting ...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    public static void addContactFlow() {
        System.out.println("Enter the First Name: ");
        String firstName = sc.nextLine();
        System.out.println("Enter the Last Name: ");
        String lastName = sc.nextLine();
        System.out.println("Enter the Phone Number: ");
        List<String> phones = Collections.singletonList(sc.nextLine());
        Contact contact = new Contact(firstName, lastName, phones, new ArrayList<>(), new HashSet<>());
        try {
            contactBook.addContact(contact, UUID.randomUUID());
            System.out.println("Contact added successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void updateContactFlow() {
        System.out.println("Enter contact id to update: ");
        UUID id = UUID.fromString(sc.nextLine());
        System.out.println("Enter new First Name: ");
        String newFirstName = sc.nextLine();
        System.out.println("Enter new Last Name: ");
        String newLastName = sc.nextLine();
        System.out.println("Enter new Phone Number: ");
        List<String> newPhones = Collections.singletonList(sc.nextLine());
        Contact newContact = new Contact(newFirstName, newLastName, newPhones, new ArrayList<>(), new HashSet<>());
        try {
            contactBook.updateContact(id, newContact);
            System.out.println("Contact updated successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void deleteContactFlow() {
        System.out.println("Enter contact id to delete: ");
        UUID id = UUID.fromString(sc.nextLine());
        try {
            contactBook.deleteContact(id);
            System.out.println("Contact deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void listAllContactFlow() {
        List<Contact> list = contactBook.listAllContacts();
        if (list.isEmpty()) {
            System.out.println("No contacts found!");
        } else {
            for (Contact c : list) {
                System.out.println(c);
            }
        }
    }
}
