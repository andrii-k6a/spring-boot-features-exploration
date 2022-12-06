package org.kook.spring.boot.exploration.config;

import lombok.extern.slf4j.Slf4j;
import org.kook.spring.boot.exploration.entity.Employee;
import org.kook.spring.boot.exploration.entity.Order;
import org.kook.spring.boot.exploration.entity.Status;
import org.kook.spring.boot.exploration.persistence.EmployeeRepository;
import org.kook.spring.boot.exploration.persistence.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class DbDataLoaderConfigs {

    /**
     * Spring Boot automatically call the run method of all beans that implements CommandLineRunner interface
     * after the application context has been loaded.
     *
     * @see CommandLineRunner
     */
    @Bean
    @ConditionalOnProperty(name = "enterprise.data.preload", havingValue = "true")
    public CommandLineRunner initDb(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
        return args -> {
            log.info("Preloading " + employeeRepository.save(new Employee("John", "Lexus", "composer")));
            log.info("Preloading " + employeeRepository.save(new Employee("Chrome", "Oxide", "artist")));
            log.info("Preloading " + employeeRepository.save(new Employee("Chandler", "Bing", "fictional character")));

            log.info("Preloading " + orderRepository.save(new Order("Spaceship", Status.IN_PROGRESS)));
            log.info("Preloading " + orderRepository.save(new Order("Car", Status.IN_PROGRESS)));
            log.info("Preloading " + orderRepository.save(new Order("Watch", Status.COMPLETED)));
            log.info("Preloading " + orderRepository.save(new Order("Love", Status.CANCELLED)));
        };
    }

}
