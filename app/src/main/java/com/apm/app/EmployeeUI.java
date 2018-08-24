package com.apm.app;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.List;
import com.apm.utility.InputUtility;
import com.apm.core.Contact;
import com.apm.core.Roles;
import com.apm.core.Employee;
import com.apm.core.EmployeeController;

public class EmployeeUI {

  public static String message = "";

  public static void printEmployeeMenu() {
    System.out.println ( "\n ======== EMPLOYEE MENU ========\n\n "
                       + " 1. Create Person \n "
                       + " 2. List All Person \n "
                       + " 3. Update Person \n "
                       + " 4. Delete Person \n "
                       + " 5. Go Back To Main Menu \n ");
  }

  public void runEmployeeUI() {
    do {
      printEmployeeMenu();
      String selection = InputUtility.inputChoice ( "Select Operation: " );
      try {
        switch ( selection ) {
          case "1":
            System.out.println ( addNewEmployee() );
            break;
          case "2":
            System.out.println ( retrieveEmployees() );
            break;
          case "3":
            getEmployeeToEdit();
            break;
          case "4":
            System.out.println ( deleteEmployee() );
            break;
          case "5":
            break;
            default:
          }
      } catch ( Exception e ) {
      }
      if ( selection.equals("5") ) {
      break;
      }
    } while ( true );
  }

  public static String addNewEmployee() throws Exception {
    System.out.print("\033\143\n");
    System.out.println( "\n ======== Add Employee ========\n\n " );
    String lastName = InputUtility.inputString ( "Input Last Name: ", false );
    String firstName = InputUtility.inputString ( "Input First Name: ", false);
    String middleName = InputUtility.inputString ( "Input Middle Name: ", false);
    String suffix = InputUtility.inputString ( "Input Suffix: ", true );
    String title = InputUtility.inputString ( "Input Title: ", true );
    Date birthDate = InputUtility.inputDate ( "Input Birth Date: " );
    Date hireDate = InputUtility.inputDate ( "Input Hire Date: " );
    float gwa = InputUtility.inputFloat ( "Input your GWA (Float) : ", false);
    boolean isCurrentlyHired = InputUtility.inputBoolean ( "Are You Currently Hired?: ");
    System.out.println ( "\n ======== ADDRESS ========\n\n ");
    int streetNumber = InputUtility.inputPositiveNumber ( "Input Street Number: ", false );
    String streetName = InputUtility.inputString ( "Input Street Name: ", false );
    String barangay = InputUtility.inputString ( "Input Barangay Name: ", false );
    String city = InputUtility.inputString ( "Input City: ", false );
    String zipCode = InputUtility.inputString ( "Input Zip Code: ", false );
    Set<Contact> contacts = ContactUI.getInstance().getAllContacts ( true );
    Set<Roles> roles = RoleUI.getInstance().getAllRoles();
    EmployeeController.addEmployee ( lastName, firstName, middleName, suffix, title, birthDate, hireDate,
                      gwa, isCurrentlyHired, EmployeeController.createNewAddress ( streetNumber, streetName,
                      barangay, city, zipCode ), contacts, roles );
    return "Employee Successfully Added! ";
    }

  public static String deleteEmployee() throws Exception {
    String message = "";
    System.out.print("\033\143\n");
    System.out.println ( "\n ======== Delete Employee ========\n\n " );
    System.out.println ( retrieveEmployees() );
    int id = InputUtility.inputPositiveNumber ( "Input ID of User To Delete: ", false);
    try {
      return EmployeeController.deleteEmployee ( id );
    } catch ( Exception e ) {
      System.out.println ( EmployeeController.message );
      throw e;
    }
  }

  public static String retrieveEmployees(){
    System.out.print("\033\143\n");
  	StringBuilder stringBuilder = new StringBuilder();
  	List<Employee> employees = EmployeeController.retrieveElements(Employee.class);
  	employees.stream()
         .sorted ( ( employee1, employee2 ) -> Long.compare ( employee1.getEmployeeId(), employee2.getEmployeeId() ) )
  			 .forEach ( employee -> stringBuilder.append ( employee + "\n" ) );
  	return stringBuilder.toString();
  }

  public static void editEmployee ( Employee employee ) throws Exception {
    do {
      printEditEmployeeMenu();
      String option = InputUtility.inputChoice ( "Select Operation: " );
      try {
        switch ( option ) {
          case "1":
            updateEmployeeDetails ( employee );
            break;
          case "2":
            employee = addEmployeeRole ( employee );
            break;
          case "3":
            employee = deleteEmployeeRole ( employee );
            break;
          case "4":
            break;
            default:
            System.out.println ( "\n Input not valid! " );
            break;
          }

        if ( option.equals("4") ) {
          break;
        } else if ( option.equals("2") || option.equals("3") ) {
          System.out.println ( EmployeeController.updateElement ( employee ) );
        }
      } catch ( Exception e ) {
        e.printStackTrace();
      }
    } while ( true );
  }

  public static void printEditEmployeeMenu(){
    System.out.println ( " \n ======== Edit Employee ======== \n\n "
                        + " 1. Edit Employee Details \n "
                        + " 2. Add Roles \n "
                        + " 3. Delete Role \n "
                        + " 4. Go Back To Employee Menu \n " );
  }

  public static void getEmployeeToEdit() throws Exception {
    do {
      Employee employee = null;
      System.out.println ( " \n ======== Choose Employee To Edit " );
      System.out.println ( retrieveEmployees() );
      int employeeId = InputUtility.inputPositiveNumber ( "Select Employee To Edit: ", false );
      try {
        employee = EmployeeController.getEmployee ( employeeId );
        if ( !employee.equals(null) ) {
          editEmployee( employee );
        }
        break;
      } catch ( Exception e ) {
        System.out.println ( "Employee does not exist!" );
        throw e;
      }
    } while ( true );
  }

  public static void updateEmployeeDetails ( Employee employee ) throws Exception {
    do {
      printUpdateEmployeeDetailsMenu();
      String option = InputUtility.inputChoice ( " \nSelect what to edit: " );
      switch ( option ) {
        case "1":
        employee = editEmployeeName ( employee );
        break;
        case "2":
        employee = editBirthDate ( employee );
        break;
        case "3":
        employee = editAddress ( employee );
        break;
        case "4":
        employee = editGwa ( employee );
        break;
        case "5":
        employee = editJobInformation ( employee );
        break;
        case "6":
        break;
        default:
        System.out.println ( "Option not Valid! ");
        break;
      }
      if ( option.equals("6") ) {
        break;
      } else {
        System.out.println( "\n" + EmployeeController.updateElement(employee));
      }
    } while ( true );
  }

  public static Employee editEmployeeName ( Employee employee ) {
    System.out.println (" \n ======== Edit Employee Name ======== \n\n ");
  	employee.setLastName ( InputUtility.inputString("Input Last Name : ", false) );
  	employee.setFirstName ( InputUtility.inputString("Input First Name : ", false) );
  	employee.setMiddleName ( InputUtility.inputString("Input Middle Name : ", false) );
  	employee.setSuffix ( InputUtility.inputString("Input Suffix : ", true) );
  	employee.setTitle ( InputUtility.inputString("Input Title : ", true) );
    return employee;
  }

  public static void printUpdateEmployeeDetailsMenu() {
  System.out.println( " \n ======== Edit Employee Details ========\n\n "
                      + " 1. Edit Name \n "
                      + " 2. Edit Birth Date \n "
                      + " 3. Edit Address \n "
                      + " 4. Edit GWA \n "
                      + " 5. Edit Job Information \n "
                      + " 6. Go Back To Edit Employee Menu ");
  }

  public static Employee editBirthDate ( Employee employee ) throws Exception {
    System.out.println ( " \n\n ======== Edit Birth Date ======== \n\n " );
    System.out.println ( "Current Birth Date: " + employee.getBirthDate() + "\n\n " );
    employee.setBirthDate ( InputUtility.inputDate("Input Birthdate (yyyy-mm-dd): ") );
    return employee;
  }

  public static Employee editAddress ( Employee employee ) throws Exception {
    System.out.println(" \n\n ======== Edit Address ======== \n\n ");
    System.out.println( "Current Address: " + employee.getAddress().toString() + "\n\n ");
    employee.getAddress().setStreetNumber(InputUtility.inputPositiveNumber("Input Street No: ", false));
  	employee.getAddress().setStreetName(InputUtility.inputString("Input Street: ", false));
  	employee.getAddress().setBarangay(InputUtility.inputString("Input Barangay: ", false));
  	employee.getAddress().setCity(InputUtility.inputString("Input City: ", false));
  	employee.getAddress().setZipCode(InputUtility.inputString("Input Zipcode: ", false));
  	return employee;
  }

  public static Employee editGwa ( Employee employee ) throws Exception {
    System.out.println ( " \n\n ======== Edit GWA ======== \n\n " );
    System.out.println ( "Current GWA: " + employee.getGwa() + "\n\n ");
    employee.setGwa ( InputUtility.inputFloat( "Input GWA: ", false ) );
    return employee;
  }

  public static Employee editJobInformation ( Employee employee ) throws Exception {
    System.out.println ( " \n\n ======== Edit Job Information ======== \n\n " );
    System.out.println ( " Currently Hired : " + employee.getIsCurrentlyHired()
                        + " \n Current Hire Date: " + employee.getHireDate() + "\n\n ");
    employee.setIsCurrentlyHired ( InputUtility.inputBoolean ( "Are You Currently Hired?: " ));
    employee.setHireDate ( InputUtility.inputDate ( "Input Hire Date: " ) );
    return employee;
  }

  public static Employee addEmployeeRole( Employee employee ) throws Exception {
    System.out.println ( " \n\n ======== Available Employee Roles ======== \n\n ");
    System.out.println ( RoleUI.getInstance().getEmployeeRolesToAdd ( employee ) );
    int id = InputUtility.inputPositiveNumber ( " Input Role ID: ", false );
    try {
      EmployeeController.getNewEmployeeRoles ( employee, id );
    } catch ( NullPointerException e ) {
      System.out.println ( "\n That Role is not available! " );
      throw e;
    }
    return employee;
  }

  public static Employee deleteEmployeeRole ( Employee employee ) throws Exception {
    System.out.println ( " \n\n ======== Available Employee Roles ======== \n\n " );
    System.out.println ( RoleUI.getInstance().getEmployeeRolesToDelete ( employee ) );
    int id = InputUtility.inputPositiveNumber ( " Input Role ID: ", false );
    try {
      EmployeeController.removeEmployeeRole ( employee, id );
    } catch ( NullPointerException e ) {
      System.out.println ( "\n Employee does not have that role! " );
      throw e;
    }
    return employee;
  }

  public static EmployeeUI getInstance() {
    return new EmployeeUI();
  }

}
