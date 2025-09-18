package mobile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class Contact implements Serializable {

    private UUID id = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private List<String> phones;
    private List<String> email;
    private Set<String> tags;
    private LocalDateTime createdAt = null;
    private LocalDateTime updatedAt;

    public Contact(String firstName, String lastName, List<String> phones, List<String> email, Set<String> tags) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones != null ? new ArrayList<>(phones) : new ArrayList<>();
        this.email = email != null ? new ArrayList<>(email) : new ArrayList<>();
        this.tags = tags != null ? new HashSet<>(tags) : new HashSet<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(UUID id, String firstName, String lastName, List<String> phones) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = phones;
    }


    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<String> getPhones() {
        return new ArrayList<>(phones);
    }

    public List<String> getEmail() {
        return new ArrayList<>(email);
    }

    public Set<String> getTags() {
        return new HashSet<>(tags);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.updatedAt = LocalDateTime.now();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
        this.updatedAt = LocalDateTime.now();
    }

    public void setEmail(List<String> email) {
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Contact {" +
                " id " + id +
                ", name " + firstName + " " + lastName +
                ", phones " + phones +
                ", email " + email +
                ", tags " + tags +
                ", createdAt " + createdAt +
                ", updatedAt " + updatedAt +
                " }";
    }

}
