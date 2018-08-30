package com.apm.app;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import com.apm.core.Roles;
import com.apm.utility.InputUtility;
import com.apm.core.Employee;
import com.apm.core.RoleController;
import com.apm.core.EmployeeController;

import org.hibernate.exception.ConstraintViolationException;

public class RoleUI {

  public static RoleController roleController = new RoleController();
  public static EmployeeController employeeController = new EmployeeController();

  public static String message = "";

  public static void printRoleMenu() {
    System.out.println ( "\n ======== ROLE MENU ========\n\n "
                       + " 1. Create Role \n "
                       + " 2. Update Role \n "
                       + " 3. List Roles \n "
                       + " 4. Delete Roles \n "
                       + " 5. Go Back To Main Menu \n ");
  }

  public void runRoleUI() {
    System.out.println (message);
    message = "";
    do {
      printRoleMenu();
      String selection = InputUtility.inputChoice( "Select Operation: " );
      try {
        switch( selection ) {
          case "1":
            System.out.println ( addNewRole() );
            break;
          case "2":
            message = updateRole();
            break;
          case "3":
            System.out.println ( retrieveAllRoles() );
            break;
          case "4":
            System.out.println ( removeRole() );
            break;
          case "5":
            break;
          default:
            System.out.println ( "\nInput not valid! ");
        }
        if ( selection.equals("5") ) {
          break;
        }
      } catch ( Exception e ) {
        System.out.println (message);
      }
    } while ( true );
  }

  public static String updateRole() throws Exception {
    try {
      System.out.println ( retrieveAllRoles() );
      Roles role = roleController.retrieveRole ( InputUtility.inputPositiveNumber ( "\n Input ID of Role to Update: ", false ) );
      message = roleController.editRole ( role, InputUtility.inputString ( "\n Input New Role Name: ", false ) ) ;
      System.out.println ( message );
    } catch ( Exception e ) {
      message = roleController.message;
      throw e;
    }
    return message;
  }

  public static String addNewRole () throws Exception {
    System.out.println ( retrieveAllRoles() );
    try {
      roleController.addRole ( InputUtility.inputString ( "\n Input New Role To Add: ", false ) );
      return "Successfully Added New Role!";
    } catch ( Exception e ) {
      System.out.println ( roleController.message );
      e.printStackTrace();
      throw e;
    }
  }

  public static String removeRole () throws Exception {
      System.out.print("\033\143\n");
      System.out.println( "\n ======== Delete Role ========\n\n " );
      System.out.println ( retrieveAllRoles() );
      int id = InputUtility.inputPositiveNumber ( "Input ID of Role To Delete: ", false);
      try {
          return roleController.deleteRole ( id );
      } catch ( ConstraintViolationException e ) {
        System.out.println ( "Role is still assigned to at least one employee!" );
        throw e;
      } catch ( Exception e ) {
        System.out.println ( roleController.message );
        throw e;
      }
  }

  public Set<Roles> getAllRoles() {
    Set<Roles> roles = new HashSet<>();
    List<Roles> availableRoles = employeeController.retrieveElements ( Roles.class );
		while ( true )	{
			System.out.println( " \n\n ======== List of Roles ======== " );
			availableRoles = availableRoles.stream()
		 						   .filter(role -> !roles.contains(role))
		  			 			 .sorted((role1,role2) -> Long.compare(role1.getRoleID(), role2.getRoleID()))
			   		  		 .collect(Collectors.toList());
			if ( availableRoles.size()==0 ) {
				break;
			}
			availableRoles.forEach ( System.out::println );
			int roleID = InputUtility.inputPositiveNumber( " Input Role To Add (Enter role number): ", false );
			try {
				roles.add ( roleController.retrieveRole ( roleID ) );
			} catch ( Exception exception ) {
				System.out.println ( "Role not found! " );
				continue;
			}
			boolean isDone = InputUtility.inputBoolean ( "Are you done adding roles (Y|N): " );
			if ( isDone && roles.size()!=0 ) {
				break;
			} else if ( isDone && roles.size()==0 ) {
				System.out.println("Please add atleast one role!");
				continue;
			}
		}
    return roles;
  }

  public static RoleUI getInstance() {
    return new RoleUI();
  }

  public static String retrieveAllRoles() throws Exception {
    System.out.print("\033\143\n");
    StringBuilder stringBuilder = new StringBuilder();
    List<Roles> roles = employeeController.retrieveElements(Roles.class);
    roles.stream()
      .sorted ( ( role1, role2 ) -> Long.compare ( role1.getRoleID(), role2.getRoleID() ) )
      .forEach ( role -> stringBuilder.append ( role + "\n" ) );
    return stringBuilder.toString();
  }

  public String getEmployeeRolesToAdd ( Employee employee ) {
		StringBuilder stringBuilder = new StringBuilder();
		employeeController.retrieveElements ( Roles.class ) .stream()
											 .filter ( role -> !employee.getRoles().contains ( role ) )
											 .sorted ( ( role1, role2 ) -> Long.compare ( role1.getRoleID(), role2.getRoleID() ) )
											 .forEach ( role -> stringBuilder.append ( role + "\n" ) );
		return stringBuilder.toString();
	}

  public String getEmployeeRolesToDelete ( Employee employee ) {
		StringBuilder stringBuilder = new StringBuilder();
		employeeController.retrieveElements ( Roles.class ) .stream()
											 .filter ( role -> employee.getRoles().contains ( role ) )
											 .sorted ( ( role1,role2 ) -> Long.compare ( role1.getRoleID(), role2.getRoleID() ) )
											 .forEach ( role -> stringBuilder.append ( role + "\n" ) );
		return stringBuilder.toString();
	}

}
