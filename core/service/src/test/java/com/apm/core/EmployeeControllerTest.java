package com.apm.core;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Set;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.MockitoAnnotations;

public class EmployeeControllerTest
{
  @Mock
  private Dao daoMock;
  @Mock
  private Employee employee;
  @Mock
  private Set<Roles> roles;
  @Mock
  private Set<Contact> contacts;
  @Mock
  private Contact contact;
  @Mock
  private Roles role;

  @InjectMocks
  private EmployeeController employeeController = mock ( EmployeeController.class );

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testAddEmployee_verifyCall() {
    employeeController.addEmployee(employee.getEmployeeName(), employee.getBirthDate(), employee.getHireDate(),
    employee.getGwa(), employee.getIsCurrentlyHired(), employee.getAddress(), employee.getContacts(), employee.getRoles());
    verify ( employeeController, times(1)).addEmployee(any(EmployeeName.class), any(Date.class),
    any(Date.class), anyFloat(), anyBoolean(), any(Address.class), any(Set.class), any(Set.class));
  }

  // @Test
  // public void testDeleteRole_Success() throws Exception {
  //   String result = "Successfully Deleted";
  //   when(roleController.deleteRole(anyInt())).thenReturn("Successfully Deleted");
  //   assertTrue(roleController.deleteRole(5).equals(result));
  // }

  @Test ( expected = NullPointerException.class )
    public void testDeleteEmployee_NullPointerException() throws Exception {
      when ( employee.getEmployeeId() ).thenReturn(Long.valueOf(1));
      when ( employeeController.deleteEmployee ( employee.getEmployeeId().intValue()+1)).thenThrow(NullPointerException.class);
      employeeController.deleteEmployee(2);
    }

    @Test
    public void testDeleteEmployee_verifyCall() throws Exception {
      employeeController.deleteEmployee(1);
      verify ( employeeController, times(1)).deleteEmployee(anyInt());
    }

    @Test
    public void testDeleteEmployee_Success() throws Exception {
      String result = "Successfully Deleted";
      when ( employeeController.deleteEmployee(anyInt())).thenReturn("Successfully Deleted");
      assertTrue( employeeController.deleteEmployee(5).equals(result));
    }

    @Test
    public void testGetEmployee_verifyCall() throws Exception {
      employeeController.getEmployee(1);
      verify ( employeeController, times(1)).getEmployee(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_employeeNotExists() throws Exception {
      when ( employeeController.getEmployee(0)).thenThrow(NullPointerException.class);
      employeeController.getEmployee(0);
    }

    @Test
    public void testGetEmployee_Success() throws Exception {
      when ( employeeController.getEmployee(anyInt())).thenReturn(employee);
      assertEquals ( employeeController.getEmployee(1), employee );
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_contactsNull() throws Exception {
      when ( employee.getContacts() ).thenThrow(NullPointerException.class);
      employee.getContacts();
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployee_rolesNull() throws Exception {
      when ( employee.getRoles() ).thenThrow(NullPointerException.class);
      employee.getRoles();
    }

    @Test
    public void testGetEmployeeWithContacts_verifyCall() throws Exception {
      employeeController.getEmployeeWithContacts(1);
      verify ( employeeController, times(1)).getEmployeeWithContacts(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployeeWithContacts_employeeNotExists() throws Exception {
      when ( employeeController.getEmployeeWithContacts(0) ).thenThrow(NullPointerException.class);
      employeeController.getEmployeeWithContacts(0);
    }

    @Test
    public void testGetEmployeeWithContacts_Success() throws Exception {
      when ( employeeController.getEmployeeWithContacts(anyInt())).thenReturn(employee);
      assertEquals ( employeeController.getEmployeeWithContacts(1), employee );
    }

    @Test
    public void testGetEmployeeWithContacts_RolesSuccess() throws Exception {
      when ( employee.getContacts() ).thenReturn(contacts);
      assertEquals ( employee.getContacts(), contacts );
    }

    @Test
    public void testGetEmployeeWithRoles_verifyCall() throws Exception {
      employeeController.getEmployeeWithRoles(1);
      verify ( employeeController, times(1)).getEmployeeWithRoles(anyInt());
    }

    @Test ( expected = NullPointerException.class )
    public void testGetEmployeeWithRoles_employeeNotExists() throws Exception {
      when ( employeeController.getEmployeeWithRoles(0) ).thenThrow( NullPointerException.class );
      employeeController.getEmployeeWithRoles(0);
    }

    @Test
    public void testGetEmployeeWithRoles_Success() throws Exception {
      when ( employeeController.getEmployeeWithRoles(anyInt())).thenReturn(employee);
      assertEquals ( employeeController.getEmployeeWithRoles(1), employee);
    }

    @Test
    public void testGetEmployeeWithRoles_RolesSuccess() throws Exception {
      when ( employee.getRoles() ).thenReturn(roles);
      assertEquals ( employee.getRoles(), roles );
    }

    @Test
    public void testCreateEmployeeName() {
      EmployeeName employeeName = mock ( EmployeeName.class );
      when ( employeeController.createEmployeeName( anyString(), anyString(), anyString(), anyString(), anyString() ) ).thenReturn(employeeName);
      assertEquals ( employeeController.createEmployeeName( "Molina", "Adrian Paolo", "Mejillano", "", "" ), employeeName );
    }

    @Test
    public void testCreateNewAddress() {
      Address address = mock ( Address.class );
      when ( employeeController.createNewAddress( anyInt(), anyString(), anyString(), anyString(), anyString() ) ).thenReturn(address);
      assertEquals ( employeeController.createNewAddress(343, "Apitong", "Comembo", "Makati", "1207" ) , address );
    }

    @Test
    public void testCreateNewContact(){
      Contact contact = mock ( Contact.class );
      when ( employeeController.createNewContact( anyString(), anyString() ) ).thenReturn(contact);
      assertEquals ( employeeController.createNewContact("MOBILE", "0937-948-7937"), contact);
    }

    @Test
    public void testRetrieveElements_verifyCall() {
      employeeController.retrieveElements(Employee.class);
      verify ( employeeController, times(1)).retrieveElements(any());
    }

    @Test
    public void testRetrieveElements_Employee() {
      List list = mock (List.class);
      when ( employeeController.retrieveElements ( any() ) ).thenReturn(list);
      assertEquals ( employeeController.retrieveElements(Employee.class), list );
    }

    @Test
    public void testRetrieveElements_Roles() {
      List list = mock (List.class);
      when ( employeeController.retrieveElements ( any() ) ).thenReturn(list);
      assertEquals ( employeeController.retrieveElements(Roles.class), list );
    }

    @Test
    public void testUpdateElement_verifyCall() {
      employeeController.updateElement(employee);
      verify ( employeeController, times(1)).updateElement(any());
    }

    @Test ( expected = NullPointerException.class )
    public void testUpdateElement_nullObject() {
      when ( employeeController.updateElement(isNull(Object.class))).thenThrow(NullPointerException.class);
      employeeController.updateElement(null);
    }

    @Test
    public void testUpdateElement_Success() {
      String result = "Update Successful";
      when ( employeeController.updateElement( any() ) ).thenReturn ("Update Successful");
      assertTrue ( employeeController.updateElement(employee).equals(result));
    }

    @Test
    public void getNewEmployeeRoles_verifyCall() throws Exception {
      employeeController.getNewEmployeeRoles ( employee, 1 );
      verify ( employeeController, times(1)).getNewEmployeeRoles(any(Employee.class), anyInt());
    }

    @Test
    public void getNewEmployeeRoles_success() throws Exception {
      when ( employeeController.getNewEmployeeRoles( any(Employee.class), anyInt() ) ).thenReturn(employee);
      assertEquals ( employeeController.getNewEmployeeRoles(employee, 1), employee);
    }

    @Test ( expected = NullPointerException.class )
    public void getNewEmployeeRoles_RoleNotExists() throws Exception {
      when ( employeeController.getNewEmployeeRoles( employee, 0 ) ).thenThrow(NullPointerException.class);
      employeeController.getNewEmployeeRoles( employee , 0);
    }

    @Test ( expected = NullPointerException.class )
    public void getNewEmployeeRoles_EmployeeNotExists() throws Exception {
      when ( employeeController.getNewEmployeeRoles( isNull(Employee.class), anyInt() ) ).thenThrow(NullPointerException.class);
      employeeController.getNewEmployeeRoles(null, 1);
    }

    @Test
    public void testRemoveEmployeeRole_verifyCall() throws Exception {
      employeeController.removeEmployeeRole(employee, 1);
      verify ( employeeController, times(1)).removeEmployeeRole(any(Employee.class), anyInt());
    }

    @Test
    public void testRemoveEmployeeRole_success() throws Exception {
      when ( employeeController.removeEmployeeRole(any(Employee.class), anyInt())).thenReturn(employee);
      assertEquals ( employeeController.removeEmployeeRole(employee, 1), employee);
    }

    @Test ( expected = NullPointerException.class )
    public void testRemoveEmployeeRole_EmployeeNotExists() throws Exception {
      when ( employeeController.removeEmployeeRole(isNull(Employee.class), anyInt())).thenThrow(NullPointerException.class);
      employeeController.removeEmployeeRole(null, 1);
    }

    @Test
    public void testAddContact() {
      when ( employeeController.addContact( any(Employee.class), anySet() ) ).thenReturn(employee);
      assertEquals ( employeeController.addContact( employee, contacts ), employee );
    }

    @Test
    public void testGetSpecificContact_verifyCall() throws Exception  {
      employeeController.getSpecificContact ( Long.valueOf(2), 1);
      verify ( employeeController, times(1)).getSpecificContact(anyLong(), anyInt());
    }

    @Test
    public void testDeleteContact() throws Exception {
      
    }


}
