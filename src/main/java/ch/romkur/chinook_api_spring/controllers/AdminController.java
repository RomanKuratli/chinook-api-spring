package ch.romkur.chinook_api_spring.controllers;

import ch.romkur.chinook_api_spring.dto.EmployeeDto;
import ch.romkur.chinook_api_spring.model.Employee;
import ch.romkur.chinook_api_spring.repos.EmployeeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
class AdminController {

    private static List<EmployeeDto> toDtoList(List<Employee> emps) {
        return emps.stream()
                .map(Employee::toDto)
                .toList();
    }

    private final EmployeeRepo employeeRepo;

    public AdminController(EmployeeRepo empRepo) {
        employeeRepo = empRepo;
    }

    @GetMapping("/employees")
    public List<EmployeeDto> getAllEmployees(
            @RequestParam(value = "search", required = false) String search
    ) {
        if (search != null && !search.trim().isEmpty()) {
            return toDtoList(employeeRepo.findByLastNameContainingIgnoreCase(search));
        }
        return toDtoList(employeeRepo.findAll());
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Integer id) {
        return ResponseEntity.of(employeeRepo.findById(id));
    }
}
