package com.apm.core;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;

public class EmployeeController {

  public Dao dao = new Dao();
  public static String message = "";

  public void addEmployee ( EmployeeName employeeName, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired,
                      Address address, Set<Contact> contacts, Set<Roles> roles ) {
      Employee employee = new Employee ( employeeName, birthDate, hireDate,
                      gwa, isCurrentlyHired, address, contacts, roles );
      contacts.forEach ( contact -> contact.setEmployee ( employee ) );
      dao.save ( employee );
  }

 public String deleteEmployee ( int id ) throws Exception {
    try {
      Employee employee = getEmployee ( id );
      return "Employee " + dao.delete ( employee );
    } catch ( NullPointerException e ) {
      message = " Employee does not exist! ";
      throw e;
    }
  }

  public Employee getEmployee ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = dao.getSpecific ( Long.valueOf ( id ) , Employee.class );
    System.out.println(employee.getEmployeeName());
    return employee;
  }

  public Employee getEmployeeWithContacts ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = dao.getSpecificWithContacts ( Long.valueOf ( id ) , Employee.class );
    return employee;
  }

  public Employee getEmployeeWithRoles ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = dao.getSpecificWithRoles ( Long.valueOf ( id ) , Employee.class );
    return employee;
  }

  public EmployeeName createEmployeeName ( String lastName, String firstName, String middleName, String suffix, String title ) {
    return new EmployeeName ( lastName, firstName, middleName, suffix, title );
  }

  public Address createNewAddress ( int streetNumber, String streetName, String barangay,
                        String city, String zipCode ) {
    return new Address ( streetNumber, streetName, barangay, city, zipCode );
  }

  public Contact createNewContact ( String contactType, String contact ) {
    return new Contact ( contactType, contact );
  }

  public <T> List<T> retrieveElements (final Class<T> type) {
    return dao.getAll(type);
  }

  public String updateElement ( Object object ) {
    dao.update ( object );
    return "Database successfully Updated!";
  }

  public Employee getNewEmployeeRoles ( Employee employee, int roleID ) throws Exception {
		Set<Roles> roles = employee.getRoles();
		Roles role = dao.getSpecific ( Long.valueOf ( roleID ), Roles.class );
    if ( role == null ) {
      throw new NullPointerException();
    } else {
      roles.add ( role );
  		employee.setRoles ( roles );
  		return employee;
    }
  }

  public Employee removeEmployeeRole ( Employee employee, int roleID ) throws Exception {
    Set<Roles> roles = employee.getRoles();
		Roles role = dao.getSpecific ( Long.valueOf ( roleID ), Roles.class );
    if ( role == null ) {
      throw new NullPointerException();
    } else {
      roles.remove ( role );
  		employee.setRoles ( roles );
  		return employee;
    }
  }

  public Employee addContact(Employee employee, Set<Contact> contacts) {
		contacts.forEach(contact -> {
			contact.setEmployee(employee);
			employee.getContacts().add(contact);
		});
		return employee;
	}

// FROM HERE

  public Contact getSpecificContact ( Long employeeId, int contactId ) throws Exception {
    Contact contact = new Contact();
    try {
      contact = dao.getSpecific ( Long.valueOf(contactId), Contact.class );
      if ( contact.getEmployee().getEmployeeId() != employeeId ) {
         message = "Employee does not own this contact info!";
        throw new RuntimeException();
      }
    } catch ( Exception e ) {
      message = "Contact does not exist!";
      throw e;
    }
    return contact;
  }

  public String deleteContact ( Employee employee, Contact contact ) throws Exception {
    if ( employee.getContacts().size() == 1 ) {
      message =  "Cannot Delete! The Contact of an Employee should at least be 1! ";
      throw new RuntimeException();
    }
    employee.getContacts().remove ( contact );
    return updateElement ( employee );
  }

  public String updateContact ( Employee employee, Contact contact, String newContact ) throws Exception {
    try {
      employee.getContacts().remove ( contact );
      contact.setContact ( newContact );
      employee.getContacts().add ( contact );
      dao.update ( contact );
      return "Successfully changed contact details to " + newContact;
    } catch ( Exception e ) {
      message = " Failed to change contact details! ";
      throw e;
    }
  }

  public List<Employee> sortByGwa ( List<Employee> employee ) {
    employee = employee.stream()
            .sorted ( ( employee1, employee2 ) -> Float.compare ( employee1.getGwa(), employee2.getGwa() ) )
            .collect ( Collectors.toList());
    return employee;
  }

  public List<Employee> sortByLastName ( List<Employee> employee ) {
    String query = "FROM Employee E ORDER BY E.employeeName.lastName ";
    employee = dao.getByQuery ( query, Employee.class );
    return employee;
  }

  public List<Employee> sortByHireDate ( List<Employee> employee ) {
    String query = "FROM Employee E ORDER BY E.hireDate";
    employee = dao.getByQuery ( query, Employee.class );
    return employee;
  }

}
