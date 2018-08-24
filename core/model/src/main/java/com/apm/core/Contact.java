package com.apm.core;

public class Contact {

  private Long contactId;
  private String contactType;
  private String contact;
  private Employee employee;

  public Contact(){
  }

  public Contact ( String contactType, String contact ) {
    this.contactType = contactType;
    this.contact = contact;
  }

  public Long getContactId() {
    return this.contactId;
  }

  public void setContactId ( Long contactId ) {
    this.contactId = contactId;
  }

  public String getContactType() {
    return this.contactType;
  }

  public void setContactType ( String contactType ) {
    this.contactType = contactType;
  }

  public String getContact() {
    return this.contact;
  }

  public void setContact ( String contact ) {
    this.contact = contact;
  }

  public Employee getEmployee() {
    return this.employee;
  }

  public void setEmployee ( Employee employee ) {
    this.employee = employee;
  }

  @Override
  public String toString() {
    return this.contactId + " -- " + this.contactType + ": " + this.contact;
  }

  @Override
    public boolean equals(Object obj) {
       if(obj == null || getClass() != obj.getClass())
         return false;

        Contact tmp = (Contact) obj;

         return this.contactType.equals(tmp.getContactType())
         && this.contact.equals(tmp.getContact());

   }

   @Override
    public int hashCode() {
        return java.util.Objects.hash(contactType, contact);
    }

}
