package com.apm.core;

import java.util.Date;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class RoleController {

  public Dao dao = new Dao();
  public static String message = "";
  public static EmployeeController employeeController = new EmployeeController();

  public void addRole ( String role ) throws Exception {
    if ( role == null ) {
      message = "Role is null! ";
      throw new NullPointerException();
    } else {
      Roles newRole = new Roles ( role );
      boolean exist = false;
      List<Roles> roles = employeeController.retrieveElements ( Roles.class );
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
  }

  public String deleteRole ( int id ) throws Exception {
    try {
      Roles role = retrieveRole ( id ) ;
      return dao.delete ( role );
    } catch ( Exception exception ) {
      throw exception;
    }
  }

  public Roles createNewRoles ( String roles ) {
    return new Roles ( roles );
  }

  public Roles retrieveRole ( int roleID ) throws Exception {
    Roles role = new Roles();
		try {
			role = dao.getSpecific ( Long.valueOf ( roleID ) , Roles.class );
		} catch ( NullPointerException e ) {
      message =  "Role does not exist! ";
      e.printStackTrace();
      throw e;
		}
		return role;
  }

  public String editRole ( Roles role, String newRoleName ) throws Exception {
    String currentRoleName = role.getRole();
  	try {
      role.setRole ( newRoleName );
			Roles newRole = dao.get ( role );
			message = "Role " + newRoleName + " already exists!" ;
    } catch ( Exception exception ) {
      if ( role == null ) {
        message = "Role is null!";
      } else if ( newRoleName.equals(null) ) {
        message = "New Role Name is null";
      } else {
        dao.update ( role );
        message = "Successfully updated role " + currentRoleName + " to " + role.getRole() + "!";
      }
		}
    return message ;
  }

}
