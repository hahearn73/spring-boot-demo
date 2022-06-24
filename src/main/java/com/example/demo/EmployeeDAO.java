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
		list.getEmployeeList().add(employee);
	}

	public void updateEmployee(Employee employee) {
		if (list.contains(employee.getId())) {
			
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
	
	// sets list to null
	public void deleteList() {
		list = null;
	}
		
}
