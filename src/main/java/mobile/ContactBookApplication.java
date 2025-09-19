package mobile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "mobile")
@SpringBootApplication
@EnableJpaRepositories(basePackages = "mobile.repo")
public class ContactBookApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContactBookApplication.class, args);
    }
}
