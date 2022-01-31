package springboot.api.bankapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.api.bankapp.data.models.Role;
import springboot.api.bankapp.data.repository.RoleRepository;

import java.util.Arrays;

@Configuration
public class InitialConfig {
    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
        return args -> {
            Role Manager = new Role(1L, "Manager");
            Role Customer = new Role(2L, "Customer");

            roleRepository.saveAll(Arrays.asList(Manager, Customer));
        };
    }

}
