package mobile.controller;

import mobile.*;
import mobile.repo.ContactRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactRepository contactRepository;
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @PostMapping
    public Contact addContact(@RequestBody Contact contact) throws InvalidInputException {
        return contactRepository.save(contact);
    }

    @GetMapping("/search")
    public  List<Contact> searchByName(@RequestParam String lastName) {
        return contactRepository.findByLastNameIgnoreCase(lastName);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable UUID id, @RequestBody Contact updated) throws InvalidInputException, DuplicateContactException {
        return contactRepository.findById(id).map(existing -> {
            existing.setFirstName(updated.getFirstName());
            existing.setLastName(updated.getLastName());
            existing.setPhones(updated.getPhones());
            return contactRepository.save(existing);
        }).orElseGet(() -> {
            updated.getId(id);
            return contactRepository.save(updated);
        });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) throws InvalidInputException, ContactNotFoundException {
        contactRepository.deleteById(id);
    }
}
