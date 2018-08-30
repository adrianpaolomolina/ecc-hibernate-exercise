package com.apm.core;

public class EmployeeName {

  private String lastName;
  private String firstName;
  private String middleName;
  private String suffix;
  private String title;

  public EmployeeName(){
  }

  public EmployeeName ( String lastName, String firstName, String middleName, String suffix, String title ) {
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.suffix = suffix;
    this.title = title;
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

  @Override
  public String toString(){
      return title + " " + lastName + ", " + firstName + " " + middleName + " " + suffix ;
  }

}
