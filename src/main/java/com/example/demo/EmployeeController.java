package com.example.demo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// Creating the REST controller
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDao;
	 
	// Implementing a GET method to get the list of all the employees
	@GetMapping(path = "/", produces = "application/json")
	public Employees getAllEmployees(Employee employee)
	{
		return employeeDao.getAllEmployees();
	}
	
	// Implementing a GET method to get an employee
	@GetMapping(path = "/", consumes = "application/json", produces = "application/json")
	public Employee getEmployee(@RequestBody Employee employee) {
		return employeeDao.getEmployee(employee);
	}
	 
	// Create a POST method to add an employee to the list
	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee)
	{
		if (employeeDao.getEmployee(employee) == null) { // avoids duplication
			// Creating an ID of an employee from the number of employees
			Integer id = employeeDao.getAllEmployees().getEmployeeList().size()	+ 1;
			employee.setId(id);
			employeeDao.addEmployee(employee);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Create a PUT method to update an existing employee to the list or add if not found
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> putEmployee(@RequestBody Employee employee) {
		employeeDao.putEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Create a PUT method to update an existing employee to the list or add if not found
	@PatchMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> patchEmployee(@RequestBody Employee employee) {
		employeeDao.updateEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Implementing a DELETE method to delete an employee, or return not found otherwise
	// SE: decrements all subsequent employees' ID number to avoid ID duplication from a post
	@DeleteMapping(path = "/", consumes = "application/json", produces = "application/json")
	public HttpStatus deleteEmployee(@RequestBody Employee employee) {
		if (employeeDao.removeEmployeeWithId(employee.getId())) {
			employeeDao.fixIds();
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
	}
	
	// Implementing a DELETE method that deletes the whole list
	@DeleteMapping(path = "/", produces = "application/json")
	public HttpStatus clearEmployees() {
		employeeDao.deleteList();
		return HttpStatus.OK;
	}

}
