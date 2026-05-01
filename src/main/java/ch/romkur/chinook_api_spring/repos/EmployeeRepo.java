package ch.romkur.chinook_api_spring.repos;

import ch.romkur.chinook_api_spring.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    List<Employee> findByLastNameContainingIgnoreCase(String name);
}
