package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.domain.Employee;

// Class to store the list of all the employees in an ArrayList
public class Employees {

	private List<Employee> employeeList = new ArrayList<Employee>();
	
	// necessary for getting all employees
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public void addEmployee(Employee employee) {
		employeeList.add(employee);
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

	// returns true if employeeList contains an employee with id id
	// false otherwise
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
		employeeList.get(0).setId(1);
		for (int i = 1; i < employeeList.size(); i++) {
			if (employeeList.get(i).getId() != employeeList.get(i - 1).getId() + 1)
				employeeList.get(i).setId(employeeList.get(i - 1).getId() + 1);
		}
	}

	// sets employee with id employee.id to all attributes
	// of employees
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
	
	// returns employee with id id
	public Employee getEmployee(int id) {
		for (Employee employee : employeeList) {
			if (employee.getId() == id) 
				return employee;
		}
		return null;
	}

	// Method to set employee with id of employee.id
	// to the same non null attributes of param
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

	// returns size of the employeeList
	public int size() {
		return employeeList.size();
	}

}
