package com.apm.utility;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

public class InputUtility
{
    public static Scanner input = new Scanner(System.in);

    public static String inputChoice ( String message ) {
      System.out.print( message );
      return input.nextLine();
    }

    public static String inputString ( String message, boolean isOptional ) {
      String data;
      do {
        System.out.print(message);
        data = input.nextLine();
      } while ( ( data == null || data.isEmpty()) && !isOptional );
      return data;
    }

    public static Date inputDate ( String message ) throws Exception {
  		String date;
  		do {
  			System.out.print(message);
  			date = input.nextLine();
  		} while(!Validate.isCorrectDate(date));
  		return new SimpleDateFormat("yyyy-MM-dd").parse(date);
  	}

    public static boolean inputBoolean ( String message ) {
  		String bool = "";
  		do {
  			System.out.print(message);
  			bool = input.nextLine().toUpperCase();
  		} while(!bool.toUpperCase().matches("Y|N"));
  		return bool.equals("Y");
  	}

    public static float inputFloat ( String message, boolean isOptional ) {
  		String temporaryData = null;
  		float value = 0;

  		do {
  			try {
  				System.out.print(message);
  				temporaryData = input.nextLine();
  				value = (Float.parseFloat(temporaryData) >= 0) ? Float.parseFloat(temporaryData) : 0;
  			} catch(Exception ex){
  				System.err.println("Input is not a valid Float!");
  				temporaryData = "";
  			}
  		}while((temporaryData == null || temporaryData.isEmpty()) && !isOptional);
  		return value;
  	}

    public static int inputPositiveNumber (String message, boolean isOptional) {
  		String temporaryData;
  		int value = 0;
  		do {
  			System.out.print(message);
  			temporaryData = input.nextLine();
  			if(isANumber(temporaryData) && !temporaryData.isEmpty() && Integer.parseInt(temporaryData) >= 0) {
  				value = Integer.parseInt(temporaryData);
  			}
  			else {
  				System.out.println("Input is not Valid!");
  				temporaryData = "";
  			}
  		} while((temporaryData== null || temporaryData.isEmpty()) && !isOptional);
  		return value;
  	}

    public static boolean isANumber(String input) {
  		try {
  			int value = Integer.parseInt(input);
  			return true;
  		} catch(NumberFormatException ex) {
  			return false;
  		}
  	}


    public static String inputLandline(String message) {
      String landline = "";
      do {
        System.out.print(message);
        landline = input.nextLine();
        if(landline.equals("")){
          break;
        }
      } while(!Validate.isCorrectLandLine(landline));
      return landline;
    }

    public static String inputMobile(String message) {
      String mobile = "";
      do {
        System.out.print(message);
        mobile = input.nextLine();
        if(mobile.equals(""))
          break;
      } while(!Validate.isCorrectMobile(mobile));
      return mobile;
    }

    public static String inputEmail(String message) {
      String email = "";
      do {
        System.out.print(message);
        email = input.nextLine();
      } while(!Validate.isCorrectEmail(email));
      return email;
    }
}
