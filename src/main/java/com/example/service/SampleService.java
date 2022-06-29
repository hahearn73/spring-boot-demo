package com.example.service;

import com.example.domain.Employee;
import com.example.repository.Employees;

public interface SampleService {

	public Employees getAllEmployees();
	
	public Employee getEmployee(Employee employee);
	
	public void	addEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void patchEmployee(Employee employee);
	
	public boolean removeEmployeeWithId(int id);
	
	public void putEmployee(Employee employee);

}
