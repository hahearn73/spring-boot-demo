package com.example.demo;

import org.springframework
	.stereotype
	.Repository;

// Importing the employees class to
// use the defined properties
// in this class
//import com.example.demo.Employees;

@Repository

// Class to create a list
// of employees
public class EmployeeDAO {

	private static Employees list = new Employees();

	// This static block is executed
	// before executing the main
	// block
	static {
		// Creating a few employees and adding them to the list
		list.getEmployeeList().add(new Employee(1, "Prem", "Tiwari", "chapradreams@gmail.com"));
		list.getEmployeeList().add(new Employee(2, "Vikash", "Kumar", "abc@gmail.com"));
		list.getEmployeeList().add(new Employee(3, "Ritesh", "Ojha", "asdjf@gmail.com")); 
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
		list.getEmployeeList().add(employee);
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
			fixIds();
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
