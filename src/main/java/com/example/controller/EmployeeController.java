package com.example.controller;

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

import com.example.domain.Employee;
import com.example.repository.Employees;
import com.example.service.impl.EmployeeDAO;

// Creating the REST controller
@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeDAO employeeDao = new EmployeeDAO();
	 
	// Implementing a GET method to get the list of all the employees
	@GetMapping(path = "/", produces = "application/json")
	public Employees getAllEmployees()
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
	public ResponseEntity<Object> addEmployee(@RequestBody Employee employee) {
		employeeDao.addEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Create a PUT method to update an existing employee on the list by replacing it with the param or add if not found
	@PutMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> putEmployee(@RequestBody Employee employee) {
		employeeDao.putEmployee(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	// Create a PATCH method that updates an employee with not null fields param that are not null
	@PatchMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> patchEmployee(@RequestBody Employee employee) {
		employeeDao.patchEmployee(employee);
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
	// removes first index and decrements all other indices, continues
	// until all other indices are removed
	@DeleteMapping(path = "/", produces = "application/json")
	public HttpStatus clearEmployees() {
		Integer size = employeeDao.size();
		for (int i = 0; i <= size; i++) {
			employeeDao.removeEmployeeWithId(i);
		}
		return HttpStatus.OK;
	}

}
