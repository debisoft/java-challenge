package jp.co.axa.apidemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	@ApiOperation(value = "List all employees", notes = "Get a list of all employees", response = Employee.class, responseContainer = "List")
	public List<Employee> getEmployees() {
		List<Employee> employees = employeeService.retrieveEmployees();
		return employees;
	}

	@GetMapping("/employees/{employeeId}")
	@ApiOperation(value = "Show employee", notes = "Get info of an employee", response = Employee.class, responseContainer = "Optional")
	public Optional<Employee> getEmployee(
			@ApiParam(value = "The assigned employee ID") @PathVariable(name = "employeeId") Long employeeId) {
		return employeeService.getEmployee(employeeId);
	}

	@PostMapping("/employees")
	@ApiOperation(value = "Save employee", notes = "Saves employee info")
	public void saveEmployee(Employee employee) {
		employeeService.saveEmployee(employee);
		System.out.println("Employee Saved Successfully");
	}

	@DeleteMapping("/employees/{employeeId}")
	@ApiOperation(value = "Delete employee", notes = "Delete an employee")
	public void deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		System.out.println("Employee Deleted Successfully");
	}

	@PutMapping("/employees/{employeeId}")
	@ApiOperation(value = "Update employee", notes = "Updates employee info")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable(name = "employeeId") Long employeeId) {
		Optional<Employee> emp = employeeService.getEmployee(employeeId);
		if (emp != null) {
			employeeService.updateEmployee(employee);
		}

	}

}
