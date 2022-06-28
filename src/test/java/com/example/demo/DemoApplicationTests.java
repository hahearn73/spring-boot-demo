package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

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
	@Order(4) // this must be last, all other tests are pure and can be in any order
	public void deleteAllGetAll() {
		controller.clearEmployees();
		assertNull(controller.getEmployee(new Employee(1)));
	}

}
