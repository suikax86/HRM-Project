package da.hms.employeeservice;

import da.hms.employeeservice.model.Role;
import da.hms.employeeservice.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class EmployeeServiceApplication implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public EmployeeServiceApplication(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }
    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleRepository.count() == 0) {
            Role role1 = new Role(1,"USER");
            Role role2 = new Role(2,"ADMIN");
            roleRepository.save(role1);
            roleRepository.save(role2);
            System.out.println("Roles saved successfully");
        }
    }
}
