package com.apm.core;

import java.util.Date;
import java.util.Set;

public class Employee {

  private Long employeeId;
  private String lastName;
  private String firstName;
  private String middleName;
  private String suffix;
  private String title;
  private Date birthDate;
  private Date hireDate;
  private float gwa;
  private boolean isCurrentlyHired;
  private Address address;
  private Set<Contact> contacts;
  private Set<Roles> roles;

  public Employee () {
  }

  public Employee ( String lastName, String firstName, String middleName, String suffix,
      String title, Date birthDate, Date hireDate, float gwa, boolean isCurrentlyHired, Address address,
       Set<Contact> contacts, Set<Roles> roles ) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.suffix = suffix;
        this.title = title;
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

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName ( String lastName ) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName ( String firstName ) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName ( String middleName ) {
    this.middleName = middleName;
  }

  public String getSuffix() {
    return this.suffix;
  }

  public void setSuffix ( String suffix ) {
    this.suffix = suffix;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle ( String title ) {
    this.title = title;
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
      return "Employee ID: " + employeeId + " Employee Name: " + lastName + ", " + firstName + " " + middleName + " " + suffix;
  }

}
