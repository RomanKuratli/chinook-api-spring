package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByLastnameContainingIgnoreCase(String name);
}
