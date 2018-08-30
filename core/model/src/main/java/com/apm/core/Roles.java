package com.apm.core;

import java.util.Set;
import java.util.HashSet;

public class Roles {

  private Long roleID;
  private String role;

  public Roles() {
  }

  public Roles ( String role ) {
    this.role = role;
  }

  public Long getRoleID() {
    return this.roleID;
  }

  public void setRoleID ( Long roleID ) {
    this.roleID = roleID;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole ( String role ) {
    this.role = role;
  }

  @Override
	public String toString() {
		return roleID + ": " + role;
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null &&!this.getClass().equals(obj.getClass()))
	    	return false;

	    Roles role2 = (Roles) obj;
	    return this.role.equals(role2.getRole());
   	}

   	@Override
   	public int hashCode() {
      	int tmp = 0;
      	tmp = (roleID + role).hashCode();
      	return tmp;
   	}
}
