package com.example.demo;

import java.util.ArrayList;
import java.util.List;

// Class to store the list of
// all the employees in an
// Array List
public class Employees {

	private List<Employee> employeeList;

	// Method to return the list
	// of employees
	public List<Employee> getEmployeeList()
	{
		if (employeeList == null) {
			employeeList = new ArrayList<Employee>();   
		}
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	// calls employeeList.remove() on the employee with id id
	public void remove(int id) {
		for (Employee emp : employeeList) {
			if (emp.getId() == id) {
				employeeList.remove(emp);
				break;
			}
		}
	}

	// returns true if employeeList contains an employee with id id, false otherwise
	public boolean contains(int id) {
		for (Employee emp : employeeList) {
			if (emp.getId() == id) {
				return true;
			}
		}
		return false;
	}

	// makes that all ids are the previous employee's id + 1
	public void fixIds() {
		for (int i = 1; i < employeeList.size(); i++) {
			if (employeeList.get(i).getId() != employeeList.get(i - 1).getId() + 1)
				employeeList.get(i).setId(employeeList.get(i - 1).getId() + 1);
		}
	}

	public boolean updateEmployee(Employee employee) {
		for (Employee emp : employeeList) {
			if (emp.equals(employee)) {
				emp.setFirstName(employee.getFirstName());
				emp.setLastName(employee.getLastName());
				emp.setEmail(employee.getEmail());
				return true;
			}
		}
		return false;
	}
	
	public Employee getEmployee(int id) {
		for (Employee employee : employeeList) {
			if (employee.getId() == id) 
				return employee;
		}
		return null;
	}

	public void patchEmployee(Employee employee) {
		for (Employee emp : employeeList) {
			if (emp.equals(employee)) {
				if (employee.getFirstName() != null)
					emp.setFirstName(employee.getFirstName());
				if (employee.getLastName() != null)
					emp.setLastName(employee.getLastName());
				if (employee.getEmail() != null)
					emp.setEmail(employee.getEmail());
			}
		}
	}

	public int size() {
		return employeeList.size();
	}

}
