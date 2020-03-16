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
  static boolean DEBUG      = false;
  static boolean FASTMODE   = false;
  static boolean LOGGED_IN  = true;
  static String  WHO_AM_I   = "InigoMontoya";
  static String  MY_SECRET  = "YouKilledMyFatherPrepareToDie";
  static final String WRITE_FILE = "../msgs/application_to_driver.txt";
  static final String READ_FILE  = "../msgs/driver_to_application.txt";

// -------|---------|---------|---------|---------|---------|---------|---------|
//
// PROGRAM DRIVER
//
// -------|---------|---------|---------|---------|---------|---------|---------|
  public static void main(String[] args) {
    System.out.println();
    System.out.println( "\u001b[37;1mWELCOME TO THE vHSM CONTROL APPLICATION: \u001b[0m" );
    System.out.println( );

    Scanner userInput = new Scanner(System.in);
    boolean isRunning = true;

    while( isRunning ) {
      renderOptions();
      String choice = userInput.next();
      System.out.println();

      // -------|---------|---------|---------|
      // SEND KEYPAIR REQUEST CASE
      // -------|---------|---------|---------|
      if( choice.equals( "L" ) ) {
        System.out.println( "---LOGIN SELECTED---" );
        System.out.println();
        System.out.println( "\u001b[31;1m\u001b[4mLogin not applicable.\u001b[0m Setting default credentials..." );
        LOGGED_IN = true;
        WHO_AM_I = "InigoMontoya";
        System.out.println();
      }

      // -------|---------|---------|---------|
      // SEND KEYPAIR REQUEST CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "M" ) ) {
        System.out.println( "---MAKE KEYPAIR SELECTED---");
        System.out.println();

        System.out.println( "PARAMETERS:" );
        System.out.println( "Username    : " + WHO_AM_I  );
        System.out.println( "Key Password: " + MY_SECRET );
        System.out.println();

        applicationWrite( "<TODO: Keypair generation request> " + WHO_AM_I + " : " + MY_SECRET );

        System.out.println( "\u001b[32;1m\u001b[4mRequest sent!\u001b[0m" );
        System.out.println();
      }

      // -------|---------|---------|---------|
      // READ KEYPAIR RESPONSE CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "R" ) ) {
        System.out.println( "---RESULT READ SELECTED---");
        System.out.println();

        // Perform the read
        String readResult = applicationRead();

        System.out.println( "-----BEGIN RESPONSE MESSAGE-----\u001b[30;1m" );
        System.out.println( readResult );
        System.out.println( "\u001b[0m-----END RESPONSE MESSAGE-----" );
        System.out.println();

        System.out.println( "\u001b[32;1m\u001b[4mRead complete!\u001b[0m" );
        System.out.println();
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
      // VERBOSITY CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "V" ) ) {
        System.out.println( "---VERBOSITY TOGGLE SELECTED---");
        System.out.println();
        if( !DEBUG ) {
          System.out.println( "Toggling verbosity (DEBUG)..." );
        }
        DEBUG = !DEBUG;

        System.out.println( "\u001b[33;1mVerbosity :\u001b[0m " + DEBUG );
        System.out.println();
        System.out.println( "\u001b[32;1m\u001b[4mVerbosity toggled!\u001b[0m" );
        System.out.println();
      }

      // -------|---------|---------|---------|
      // BAD INPUT CASE
      // -------|---------|---------|---------|
      else {
        System.out.println( "Selection not recognized or invalid (are you logged in?)." );
        System.out.println();
      }



    }
  }

//-------|---------|---------|---------|---------|---------|---------|---------|
//
// SUPPORT FUNCTIONS
//
//-------|---------|---------|---------|---------|---------|---------|---------|

//-------|---------|---------|---------|---------|---------|---------|---------|
//
// READERS/LOADERS
//
//-------|---------|---------|---------|---------|---------|---------|---------|

//-------|---------|---------|---------|
// applicationRead()
//-------|---------|---------|---------|  
  public static String applicationRead( ) {
    String result = readFileToString( READ_FILE );
    return result;
  }

//-------|---------|---------|---------|
// applicationWrite()
//-------|---------|---------|---------|  
  public static void applicationWrite( String message ) {
    writeStringToFile( message, WRITE_FILE );
  }

//-------|---------|---------|---------|
// readFileToString()
//-------|---------|---------|---------|  
// Reads a file and returns the contents as a String
  public static String readFileToString( String filename ) {    
    File f = new File( filename );
    Scanner fileReader = null;
    try {
      fileReader = new Scanner( f );
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String retString = "";
    while( fileReader.hasNextLine() ) {
      retString += fileReader.nextLine();
    }
    if( DEBUG ) {
      System.out.println( "  [readFile()] - Read File: " + filename );
      System.out.println( "  [readFile()] - Contents : " );
      System.out.println( retString );
      System.out.println();
    }
    return retString;
  } // Closing readFile()

//-------|---------|---------|---------|
// writeStringToFile()
//-------|---------|---------|---------|  
// Takes a String and writes to file
  public static void writeStringToFile( String text, String filename ) {    
    FileWriter fw = null;
    BufferedWriter writer = null;
    try {
      fw = new FileWriter( filename );
      writer = new BufferedWriter( fw );
      writer.write( text );
      writer.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  } // Closing writeStringToFile()

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
    System.out.println( "|   \u001b[1mL\u001b[0m  - \u001b[4mL\u001b[0mogin                                                                  |" );
    if( LOGGED_IN ) {
      System.out.println( "|   \u001b[1mM\u001b[0m  - \u001b[4mM\u001b[0make Key                                                               |" );
      System.out.println( "|   \u001b[1mR\u001b[0m  - \u001b[4mR\u001b[0mead Key                                                               |" );
    }
    else if( !LOGGED_IN ) {
      System.out.println( "|   \u001b[30;1mM  - (Unavailable - Please log in) Make Key \u001b[0m                              |" );
      System.out.println( "|   \u001b[30;1mR  - (Unavailable - Please log in) Read Key \u001b[0m                 |" );
    }
    System.out.println( "|   \u001b[1mV\u001b[0m  - \u001b[4mV\u001b[0merbosity (toggle)                                                     |" );
    System.out.println( "|   \u001b[1mX\u001b[0m  - e\u001b[4mX\u001b[0mit                                                                   |" );
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