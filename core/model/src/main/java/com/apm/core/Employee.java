package com.apm.core;

import java.util.Date;
import java.util.Set;

public class Employee {

  private Long employeeId;
  private EmployeeName employeeName;
  private Date birthDate;
  private Date hireDate;
  private float gwa;
  private boolean isCurrentlyHired;
  private Address address;
  private Set<Contact> contacts;
  private Set<Roles> roles;

  public Employee () {
  }

  public Employee ( EmployeeName employeeName, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired, Address address,
       Set<Contact> contacts, Set<Roles> roles ) {
        this.employeeName = employeeName;
        this.birthDate = birthDate;
        this.hireDate = hireDate;
        this.gwa = gwa;
        this.isCurrentlyHired = isCurrentlyHired;
        this.address = address;
        this.contacts = contacts;
        this.roles = roles;
  }

  public Long getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId ( Long employeeId ) {
    this.employeeId = employeeId;
  }

  public EmployeeName getEmployeeName() {
    return this.employeeName;
  }

  public void setEmployeeName ( EmployeeName employeeName ) {
    this.employeeName = employeeName;
  }

  public Date getBirthDate() {
    return this.birthDate;
  }

  public void setBirthDate ( Date birthDate ) {
    this.birthDate = birthDate;
  }

  public Date getHireDate() {
    return this.hireDate;
  }

  public void setHireDate ( Date hireDate ) {
    this.hireDate = hireDate;
  }

  public float getGwa() {
    return this.gwa;
  }

  public void setGwa ( float gwa ) {
    this.gwa = gwa;
  }

  public boolean getIsCurrentlyHired() {
    return this.isCurrentlyHired;
  }

  public void setIsCurrentlyHired ( boolean isCurrentlyHired ) {
    this.isCurrentlyHired = isCurrentlyHired;
  }

  public Set<Contact> getContacts() {
    return this.contacts;
  }

  public void setContacts ( Set<Contact> contacts ) {
    this.contacts = contacts;
  }

  public Set<Roles> getRoles() {
    return this.roles;
  }

  public void setRoles ( Set<Roles> roles ) {
    this.roles = roles;
  }

  public Address getAddress() {
    return this.address;
  }

  public void setAddress ( Address address ) {
    this.address = address;
  }

  @Override
  public String toString(){
      return "Employee ID: " + employeeId + " Employee Name: " + employeeName ;
  }

}
