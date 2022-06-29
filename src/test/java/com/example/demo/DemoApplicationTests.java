package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.controller.EmployeeController;
import com.example.domain.Employee;
import com.example.repository.Employees;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class) // used to ensure delete all happens last
class DemoApplicationTests {

	private EmployeeController controller = new EmployeeController();
	
	@Test
	@Order(1)
	public void postGetDeleteEmployee() {
		controller.addEmployee(new Employee(4, "harry", "ahearn", "mail"));
		Employee emp = controller.getEmployee(new Employee(4));
		assertEquals(emp.getId(), 4);
		assertEquals(emp, new Employee(4, "jason", null, null)); 	// equal bc same id
		assertNotEquals(emp, new Employee(2, "jason", null, null)); // not equal bc diff id
		assertEquals(emp.getEmail(), "mail");
		assertEquals(emp.getFirstName(), "harry");
		assertEquals(emp.getLastName(), "ahearn");
		controller.deleteEmployee(emp);
		assertNull(controller.getEmployee(new Employee(4)));
	}
	
	@Test
	@Order(2)
	public void patchAndGetEmployee() {
		Employee emp = new Employee(4, "barry", null, null);
		controller.addEmployee(emp);
		emp = new Employee(4, "harry", "ahearn", "mail");
		controller.patchEmployee(emp);
		emp = controller.getEmployee(new Employee(4));
		assertEquals(emp.getFirstName(), "harry");
		assertNotNull(emp.getLastName());
		assertNotNull(emp.getEmail());
	}
	
	@Test
	@Order(3)
	public void putAndGetEmployee() {
		Employee emp = new Employee(4, "barry", "asdf", "asdf");
		controller.addEmployee(emp);
		controller.putEmployee(new Employee(4, null, null, null));
		emp = controller.getEmployee(new Employee(4));
		assertEquals(emp.getId(), 4);
		assertNull(emp.getFirstName());
		assertNull(emp.getLastName());
		assertNull(emp.getEmail());
		controller.deleteEmployee(emp);
	}
	
	@Test
	@Order(4)
	public void clearGetAll() {
		controller.clearEmployees();
		assertNull(controller.getEmployee(new Employee(1)));
	}
	
	@Test
	@Order(5)
	public void postPutPatchAfterClear() {
		controller.putEmployee(new Employee(3));
		assertNotNull(controller.getEmployee(new Employee(1))); // gets put at front
		controller.clearEmployees();
		controller.patchEmployee(new Employee(3));
		assertNotNull(controller.getEmployee(new Employee(1))); // gets put at front
		controller.clearEmployees();
		controller.addEmployee(new Employee(3));
		assertNotNull(controller.getEmployee(new Employee(1))); // gets put at front
	}
	
	@Test
	@Order(6)
	public void deleteTests() {
		controller.clearEmployees();
		Employee a = new Employee(1, "a", "a", "a");
		Employee b = new Employee(2, "b", "b", "b");
		Employee c = new Employee(3, "c", "c", "c");
		
		controller.addEmployee(a);
		controller.addEmployee(b);
		controller.addEmployee(c);
		
		// delete middle
		controller.deleteEmployee(new Employee(2));
		a = controller.getEmployee(new Employee(1));
		assertEquals(a.getFirstName(), "a");
		c = controller.getEmployee(new Employee(2));
		assertEquals(c.getFirstName(), "c");
		assertNull(controller.getEmployee(new Employee(3)));
		
		// reset
		controller.clearEmployees();
		a = new Employee(1, "a", "a", "a");
		b = new Employee(2, "b", "b", "b");
		c = new Employee(3, "c", "c", "c");
		controller.addEmployee(a);
		controller.addEmployee(b);
		controller.addEmployee(c);
		
		// delete tail
		controller.deleteEmployee(c);
		a = controller.getEmployee(new Employee(1));
		assertEquals(a.getFirstName(), "a");
		b = controller.getEmployee(new Employee(2));
		assertEquals(b.getFirstName(), "b");
		assertNull(controller.getEmployee(new Employee(3)));
		
		// reset
		controller.clearEmployees();
		a = new Employee(1, "a", "a", "a");
		b = new Employee(2, "b", "b", "b");
		c = new Employee(3, "c", "c", "c");
		controller.addEmployee(a);
		controller.addEmployee(b);
		controller.addEmployee(c);
		
		// delete head
		controller.deleteEmployee(a);
		b = controller.getEmployee(new Employee(1));
		assertEquals(b.getFirstName(), "b");
		c = controller.getEmployee(new Employee(2));
		assertEquals(c.getFirstName(), "c");
		assertNull(controller.getEmployee(new Employee(3)));
	}
	
	@Test
	@Order(7)
	public void getAllTest() {
		controller.clearEmployees();
		Employee a = new Employee(1, "a", "a", "a");
		Employee b = new Employee(2, "b", "b", "b");
		Employee c = new Employee(3, "c", "c", "c");
		controller.addEmployee(a);
		controller.addEmployee(b);
		controller.addEmployee(c);
		
		Employees list = new Employees();
		list.addEmployee(a);
		list.addEmployee(b);
		list.addEmployee(c);
		Employees ret = controller.getAllEmployees();
		
		for (int i = 1; i < list.size(); i++) {
			assert(ret.getEmployee(i).equals(ret.getEmployee(i)));
			assert(ret.getEmployee(i).getFirstName().equals(ret.getEmployee(i).getFirstName()));
		}
	}

}
