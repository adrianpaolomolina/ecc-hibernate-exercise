package com.apm.core;

import java.util.Date;
import java.util.Set;
import java.util.List;

public class EmployeeController {

  public static Dao dao = new Dao();
  public static String message = "";

  public static void addEmployee ( String lastName, String firstName, String middleName, String suffix,
                      String title, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired,
                      Address address, Set<Contact> contacts, Set<Roles> roles ) {
      Employee employee = new Employee ( lastName, firstName, middleName, suffix, title, birthDate, hireDate,
                      gwa, isCurrentlyHired, address, contacts, roles );
      contacts.forEach ( contact -> contact.setEmployee ( employee ) );
      dao.save ( employee );
  }

  public static void addRole ( String role ) throws Exception {
    Roles newRole = new Roles ( role );
    boolean exist = false;
    List<Roles> roles = retrieveElements ( Roles.class );
    for ( Roles currentRoles : roles ) {
      if ( currentRoles.getRole().equals ( newRole.getRole() ) ) {
        exist = true;
        break;
      }
    }
    if ( exist == false ) {
     dao.save ( newRole );
    } else if ( exist == true ){
      message = " Role already exists! ";
      throw new Exception();
    }
  }

 public static String deleteEmployee ( int id ) throws Exception {
    try {
      Employee employee = getEmployee ( id );
      return "Employee " + dao.delete ( employee );
    } catch ( Exception e ) {
      message = " Employee does not exist! ";
      throw e;
    }
  }

  public static String deleteRole ( int id ) throws Exception {
    try {
      Roles role = retrieveRole ( id ) ;
      return dao.delete ( role );
    } catch ( Exception exception ) {
      throw exception;
    }
  }

  public static Employee getEmployee ( int id ) throws Exception {
    Employee employee = new Employee();
    employee = dao.getSpecific ( Long.valueOf ( id ) , Employee.class );
    return employee;
  }

  public static Address createNewAddress ( int streetNumber, String streetName, String barangay,
                        String city, String zipCode ) {
    return new Address ( streetNumber, streetName, barangay, city, zipCode );
  }

  public static Contact createNewContact ( String contactType, String contact ) {
    return new Contact ( contactType, contact );
  }

  public static Roles createNewRoles ( String roles ) {
    return new Roles ( roles );
  }

  public static <T> List<T> retrieveElements (final Class<T> type) {
    return dao.getAll(type);
  }

  public static String updateElement ( Object object ) {
    dao.update ( object );
    return "Database successfully Updated!";
  }

  public static Employee getNewEmployeeRoles ( Employee employee, int roleID ) throws Exception {
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

  public static Employee removeEmployeeRole ( Employee employee, int roleID ) throws Exception {
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

  public static Roles retrieveRole ( int roleID ) throws Exception {
    Roles role = new Roles();
		try {
			role = dao.getSpecific ( Long.valueOf ( roleID ) , Roles.class );
			role.getRole();
		} catch ( NullPointerException e ) {
      message =  "Role does not exist! ";
      throw e;
		}
		return role;
  }

  public static Employee addContact(Employee employee, Set<Contact> contacts) {
		contacts.forEach(contact -> {
			contact.setEmployee(employee);
			employee.getContacts().add(contact);
		});
		return employee;
	}

  public static Contact getSpecificContact ( Long employeeId, int contactId ) throws Exception {
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

  public static String deleteContact ( Employee employee, Contact contact ) throws Exception {
    if ( employee.getContacts().size() == 1 ) {
      message =  "Cannot Delete! The Contact of an Employee should at least be 1! ";
      throw new RuntimeException();
    }
    employee.getContacts().remove ( contact );
    return updateElement ( employee );
  }

  public static String editRole ( Roles role, String newRoleName ) throws Exception {
    String currentRoleName = role.getRole();
    role.setRole ( newRoleName );
		try {
			Roles newRole = dao.get ( role );
			message = "Role " + newRoleName + " already exists!" ;
    } catch ( Exception exception ) {
      dao.update ( role );
      message = "Successfully updated role " + currentRoleName + " to " + role.getRole() + "!";
		}
    return message ;
  }

  public static String updateContact ( Employee employee, Contact contact, String newContact ) throws Exception {
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


}
