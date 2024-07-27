package da.hms.employeeservice.controller;

import da.hms.employeeservice.model.Employee;
import da.hms.employeeservice.model.dto.EmployeeDto;
import da.hms.employeeservice.repository.EmployeeRepository;
import da.hms.employeeservice.service.RewardPointsServiceClient;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository employeeRepository;
    private final RewardPointsServiceClient rewardPointsServiceClient;

    public EmployeeController(EmployeeRepository employeeRepository, RewardPointsServiceClient rewardPointsServiceClient) {
        this.employeeRepository = employeeRepository;
        this.rewardPointsServiceClient = rewardPointsServiceClient;
    }

    @GetMapping("/")
    public List<Employee> getEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
        for(Employee employee : employees) {
            try {
                int points = rewardPointsServiceClient.getRewardPoints(employee.getId());
                employee.setRewardPoints(points);
            }
            catch (Exception e) {
                System.err.println("Failed to fetch reward points: " + e.getMessage());
                employee.setRewardPoints(-1);
            }
        }
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = this.employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        } else {
            try{
                int points = rewardPointsServiceClient.getRewardPoints(id);
                employee.setRewardPoints(points);
            } catch (Exception e) {
                System.err.println("Failed to fetch reward points: " + e.getMessage());
                employee.setRewardPoints(-1);
            }
            return employee;
        }
    }

    @PostMapping("/")
    public Employee addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

        // Check if the employee already exists by idNumber
        if(employeeRepository.existsByIdNumber(employeeDto.getIdNumber())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Employee with the same idNumber already exists");
        }

        Employee employee = new Employee(employeeDto.getName(), employeeDto.getEmail(), employeeDto.getIdNumber(), employeeDto.getTaxNumber(), employeeDto.getAddress(), employeeDto.getPhoneNumber(), employeeDto.getBankName(),employeeDto.getBankNumber());

        return employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @Valid @RequestBody EmployeeDto employeeDto) {
        Employee employee = this.employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }

        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setIdNumber(employeeDto.getIdNumber());
        employee.setTaxNumber(employeeDto.getTaxNumber());
        employee.setAddress(employeeDto.getAddress());
        employee.setPhoneNumber(employeeDto.getPhoneNumber());
        employee.setBankNumber(employeeDto.getBankNumber());

        return employeeRepository.save(employee);
    }

}
