package springboot.api.bankapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.repository.*;

import java.util.Arrays;

@Configuration
public class InitialConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            // Roles
            Role customer = new Role(1000000000L, "User");
            Role manager = new Role(2000000000L, "Manager");
            roleRepository.saveAll(Arrays.asList(customer, manager));
        };
    }
}
