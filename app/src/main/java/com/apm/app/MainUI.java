package com.apm.app;

import com.apm.utility.InputUtility;

public class MainUI
{
    public static void printChooseFunction() {
      System.out.println ( " ======== MAIN MENU ======== \n\n "
                         + " 1. Employees \n "
                         + " 2. Contacts \n "
                         + " 3. Roles \n "
                         + " 4. Exit \n "
                         );
    }

    public static void MainOperation() {
      do {
        System.out.print("\033\143\n");
        printChooseFunction();
        String selection = InputUtility.inputChoice("Select Operation: ");
        switch(selection){
          case "1":
            EmployeeUI.getInstance().runEmployeeUI();
            break;
          case "2":
            ContactUI.getInstance().runContactUI();
            break;
          case "3":
            RoleUI.getInstance().runRoleUI();
            break;
          case "4":
            System.out.println("Thank you for using the program.");
            System.exit(0);
          default:
            System.out.println("Invalid Input!");
            break;
        }
      } while ( true );
    }

    public static void main( String[] args )
    {
        MainOperation();
    }
}
