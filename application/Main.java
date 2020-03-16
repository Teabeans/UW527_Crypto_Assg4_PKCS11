//-------|---------|---------|---------|---------|---------|---------|---------|
//
// UW CSS 527 - Assg4 - PKCS#11
// application/Main.java
//
//-------|---------|---------|---------|---------|---------|---------|---------|

//-----------------------------------------------------------------------------|
// Authorship
//-----------------------------------------------------------------------------|
//
// Tim Lum
// twhlum@gmail.com
//
// Created:  2020.03.14
// Modified: 2020.03.15
// For the University of Washington Bothell, CSS 527
// Winter 2020, Masters in Cybersecurity Engineering (MCSE)
//

//-----------------------------------------------------------------------------|
// File Description
//-----------------------------------------------------------------------------|
//
// Driver for a mock application which attempts to communicate to an HSM

//-----------------------------------------------------------------------------|
// Package Files
//-----------------------------------------------------------------------------|
//
// See code repository at: https://github.com/Teabeans/UW527_Crypto_Assg4_PKCS11

//-----------------------------------------------------------------------------|
// Useage
//-----------------------------------------------------------------------------|
//
// Compile with:
// $ javac Main.java && java Main
//
// Note: Requires Java SDK installed to the Linux environment. Install with:
// $ sudo apt-get update
// $ sudo apt-get install openjdk-8-jdk
//
// If using compilation script, edit script permission with:
// $ chmod u+x compile.sh
// Then run using:
// $ ./compile.sh

//-------|---------|---------|---------|---------|---------|---------|---------|
//
//       INCLUDES
//
//-------|---------|---------|---------|---------|---------|---------|---------|

import java.io.File;                  // For file operations
import java.io.FileNotFoundException; // For file exception
import java.io.IOException;           // For buffered writer
import java.io.BufferedWriter;        // For buffered writing
import java.io.FileWriter;            // For buffered writing
import java.util.Iterator;            // For iterators
import java.util.Scanner;             // For user inputs

public class Main {

// -------|---------|---------|---------|---------|---------|---------|---------|
//
// GLOBAL CONSTANTS
//
// -------|---------|---------|---------|---------|---------|---------|---------|
  static boolean DEBUG      = true;
  static boolean FASTMODE   = false;
  static boolean LOGGED_IN  = true;
  static String  WHO_AM_I   = "InigoMontoya";
  static String  MY_SECRET  = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b"; // TODO: Validate, may not be necessary for application
  static final String WRITE_FILE = "../msgs/application_to_driver.txt";
  static final String READ_FILE  = "../msgs/driver_to_application.txt";

// -------|---------|---------|---------|---------|---------|---------|---------|
//
// PROGRAM DRIVER
//
// -------|---------|---------|---------|---------|---------|---------|---------|
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);
    boolean isRunning = true;
    while( isRunning ) {
      renderOptions();

      System.out.println( "Hi! I am a host/application!" );
      System.out.println( "I want to make a call to the HSM." );

      String choice = userInput.next();
      System.out.println();

      // -------|---------|---------|---------|
      // SEND KEYPAIR REQUEST CASE
      // -------|---------|---------|---------|
      if( choice.equals( "M" ) ) {
        // (M)ake a keypair
      }

      // -------|---------|---------|---------|
      // READ KEYPAIR RESPONSE CASE
      // -------|---------|---------|---------|
      if( choice.equals( "R" ) ) {
        // (R)ead results
      }

      // -------|---------|---------|---------|
      // EXIT CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "X" ) ) {
        System.out.println( "---EXIT SELECTED---");
        System.out.println();
        isRunning = false;
      }

      // -------|---------|---------|---------|
      // BAD INPUT CASE
      // -------|---------|---------|---------|
      else {
        System.out.println( "Selection not recognized or invalid (are you logged in?)." );
        System.out.println();
      }

      System.out.println( "I will send an application-specific request to the driver." );
      applicationWrite();

      System.out.println( "I can also read application-specific responses from the driver." );
      applicationRead();
    }
  }

//-------|---------|---------|---------|---------|---------|---------|---------|
//
// SUPPORT FUNCTIONS
//
//-------|---------|---------|---------|---------|---------|---------|---------|
  public static void applicationRead( ) {
    System.out.println( "This is a read of a message from a driver" );
    System.out.println( "Please read me to msgs/driver_to_application.txt" );
  }

  public static void applicationWrite( ) {
  	System.out.println( "This is an application-specific message being sent to the driver" );
  	System.out.println( "Please write me to msgs/application_to_driver.txt" );
  }

//-------|---------|---------|---------|---------|---------|---------|---------|
//
// RENDERERS
//
//-------|---------|---------|---------|---------|---------|---------|---------|

//-------|---------|---------|---------|
// renderOptions()
//-------|---------|---------|---------|
  public static void renderOptions() {
    long delay = 10;
    System.out.println( "+-------------------------------------------------------------------------------+" );
    System.out.println( "|   OPTIONS                                                                     |" );
    System.out.println( "+-------------------------------------------------------------------------------+" );
    System.out.println( "|   \u001b[1mN\u001b[0m  - make a \u001b[4mN\u001b[0mew user account                                                |" );
    System.out.println( "|   \u001b[1mL\u001b[0m  - \u001b[4mL\u001b[0mogin                                                                  |" );
    System.out.println( "|   \u001b[1mR\u001b[0m  - \u001b[4mR\u001b[0meport the contents of the vHSM                                        |" );
    if( LOGGED_IN ) {
      System.out.println( "|   \u001b[1mC\u001b[0m  - \u001b[4mC\u001b[0mreate Key                                                             |" );
      System.out.println( "|   \u001b[1mE\u001b[0m  - \u001b[4mE\u001b[0mncrypt (w/private key)                                                |" );
      System.out.println( "|   \u001b[1mD\u001b[0m  - \u001b[4mD\u001b[0mecrypt (w/public  key)                                                |" );
    }
    else if( !LOGGED_IN ) {
      System.out.println( "|   \u001b[30;1mC  - (Unavailable - Please log in) Create Key \u001b[0m                              |" );
      System.out.println( "|   \u001b[30;1mE  - (Unavailable - Please log in) Encrypt (w/private key) \u001b[0m                 |" );
      System.out.println( "|   \u001b[30;1mD  - (Unavailable - Please log in) Decrypt (w/public  key) \u001b[0m                 |" );
    }
    System.out.println( "|   \u001b[1mT\u001b[0m  - \u001b[4mT\u001b[0mare HSM (drop tables)                                                 |" );
    System.out.println( "|   \u001b[1mV\u001b[0m  - \u001b[4mV\u001b[0merbosity (toggle)                                                     |" );
    System.out.println( "|   \u001b[1mX\u001b[0m  - e\u001b[4mX\u001b[0mit                                                                   |" );
    System.out.println( "|   \u001b[1mSX\u001b[0m - \u001b[4mS\u001b[0mave + e\u001b[4mX\u001b[0mit                                                            |" );
    System.out.println( "+-------------------------------------------------------------------------------+" );
    System.out.println();
    System.out.print( "\u001b[37;1mPlease select an option: \u001b[0m" );
  } // Closing renderOptions()

//-------|---------|---------|---------|---------|---------|---------|---------|
//
// FORMAT CONVERSION
//
//-------|---------|---------|---------|---------|---------|---------|---------|

// None yet identified

} // Closing class Main

// End of file - application/Main.java