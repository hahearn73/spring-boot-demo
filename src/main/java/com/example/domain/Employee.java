package com.example.domain;

// Creating an entity Employee
public class Employee {

	public Employee() {}

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	
	// Parameterized Constructor
	// to assign the values
	// to the properties of
	// the entity
	public Employee(Integer id, String firstName, String lastName, String email)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Employee(int id) {
		this(id, null, null, null);
	}
	
	// Employee objects are equivalent if their id's match
	@Override
	public boolean equals(Object employee) {
		return this.id == ((Employee) employee).getId();
	}
	
	// ensures that hash codes are equivalent between equivalent employees
	@Override
    public int hashCode() {
        return this.id;
    }

	// Overriding the toString method
	// to find all the values
	@Override
	public String toString()
	{
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}

	// Getters and setters of
	// the properties
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
}
