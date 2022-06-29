package com.example.service.impl;

import org.springframework.stereotype.Repository;

import com.example.domain.Employee;
import com.example.repository.Employees;
import com.example.service.SampleService;


@Repository

// Class to create a list
// of employees
public class EmployeeDAO implements SampleService {

	private static Employees list = new Employees();
	
	static {
		
		// starter data for testing; comment out for real use
		list.addEmployee(new Employee(1, "Prem", "Tiwari", "chapradreams@gmail.com"));
		list.addEmployee(new Employee(2, "Vikash", "Kumar", "abc@gmail.com"));
		list.addEmployee(new Employee(3, "Ritesh", "Ojha", "asdjf@gmail.com")); 
	}

	// Method to return the list
	public Employees getAllEmployees() {
		return list;
	}
	
	// Method to return a single employee
	public Employee getEmployee(Employee employee) {
		return list.getEmployee(employee.getId());
	}

	 
	// Method to add an employee to the employees list
	public void	addEmployee(Employee employee) {
		if (employee.getId() == null || getEmployee(employee) == null) { // if doesn't exist
			Integer id = list.size() + 1; // give it an id
			employee.setId(id);
		}
		list.addEmployee(employee);
	}

	// Method to update all attributes of employee that
	// matches employee.id with new attributes
	public void updateEmployee(Employee employee) {
		if (employee.getId() == null) { // no id -> post request
			addEmployee(employee);
			return;
		}
		
		if (list.contains(employee.getId())) {
			list.updateEmployee(employee);
		}
		else
			this.addEmployee(employee);
	}
	
	// Method to patch give employee with employee.id
	// the same non null attributes as employee
	public void patchEmployee(Employee employee) {
		if (employee.getId() == null) { // no id -> post request
			addEmployee(employee);
			return;
		}
			
		if (list.contains(employee.getId())) {
			list.patchEmployee(employee);
		}
		else
			this.addEmployee(employee);
	}
	
	// Method to remove an employee from the employees list	,
	// returns true if successful, false if employee doesn't exist
	public boolean removeEmployeeWithId(int id) {
		if (list.contains(id)) {
			list.remove(id);
			return true;
		}
		return false;
	}

	// calls list.fixIds()
	public void fixIds() {
		list.fixIds();
	}

	// looks to update employee in list, if not found it adds it to list
	public void putEmployee(Employee employee) {
		if (!list.updateEmployee(employee)) {
			this.addEmployee(employee);
		}
	}

	public Integer size() {
		return list.size();
	}
		
}
