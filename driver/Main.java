//-------|---------|---------|---------|---------|---------|---------|---------|
//
// UW CSS 527 - Assg4 - PKCS#11
// driver/Main.java
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
// Driver for a mock Driver which attempts to communicate to an HSM and Application

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
  static boolean DEBUG          = false;
  static boolean FASTMODE       = false;
  static boolean LOGGED_IN      = true;
  static String  IDENTITY_TOKEN = "InigoMontoya"; // Will be reassigned from the vHSM when requested
  static final String APP_READ_FILE   = "../msgs/application_to_driver.txt";
  static final String APP_WRITE_FILE  = "../msgs/driver_to_application.txt";
  static final String VHSM_READ_FILE  = "../msgs/vHSM_to_driver.txt";
  static final String VHSM_WRITE_FILE = "../msgs/driver_to_vHSM.txt";

// -------|---------|---------|---------|---------|---------|---------|---------|
//
// PROGRAM DRIVER
//
// -------|---------|---------|---------|---------|---------|---------|---------|

  public static void main(String[] args) {
    System.out.println();
    System.out.println( "\u001b[37;1mWELCOME TO THE vHSM DRIVER EXCHANGE INTERFACE: \u001b[0m" );
    System.out.println( "I shunt requests between an application and compliant vHSM" );
    System.out.println( );

    Scanner userInput = new Scanner(System.in);
    boolean isRunning = true;

    while( isRunning ) {
      renderOptions();
      String choice = userInput.next();
      System.out.println();

      // -------|---------|---------|---------|
      // APPLICATION REQUEST READ CASE
      // -------|---------|---------|---------|
      if( choice.equals( "AR" ) ) {
        System.out.println( "---READ FROM APPLICATION SELECTED---");
        System.out.println();

        // Perform the read
        String readResult = driverReadFromvHSM();

        System.out.println( "-----BEGIN RESPONSE MESSAGE-----\u001b[30;1m" );
        System.out.println( readResult );
        System.out.println( "\u001b[0m-----END RESPONSE MESSAGE-----" );
        System.out.println();

        System.out.println( "\u001b[32;1m\u001b[4mRead from application complete!\u001b[0m" );
        System.out.println();
      }

      // -------|---------|---------|---------|
      // vHSM REQUEST WRITE CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "VW" ) ) {
        System.out.println( "---WRITE TO VHSM SELECTED---");
        System.out.println();

        System.out.println( "PARAMETERS:" );
        System.out.println( "Username: " + IDENTITY_TOKEN  );
        System.out.println();

        driverWriteTovHSM( "<TODO: Compliant key creation request> " + IDENTITY_TOKEN );

        System.out.println( "\u001b[32;1m\u001b[4mRequest sent to vHSM!\u001b[0m" );
        System.out.println();
      }

      // -------|---------|---------|---------|
      // vHSM RESULT READ CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "VR" ) ) {
        System.out.println( "---READ FROM vHSM SELECTED---");
        System.out.println();

        // Perform the read
        String readResult = driverReadFromvHSM();

        System.out.println( "-----BEGIN RESPONSE MESSAGE-----\u001b[30;1m" );
        System.out.println( readResult );
        System.out.println( "\u001b[0m-----END RESPONSE MESSAGE-----" );
        System.out.println();

        System.out.println( "\u001b[32;1m\u001b[4mRead from vHSM complete!\u001b[0m" );
        System.out.println();
      }

      // -------|---------|---------|---------|
      // APPLICATION RESULT WRITE CASE
      // -------|---------|---------|---------|
      else if( choice.equals( "AW" ) ) {
        System.out.println( "---WRITE TO APPLICATION SELECTED---");
        System.out.println();

        System.out.println( "PARAMETERS:" );
        System.out.println( "Username: " + IDENTITY_TOKEN  );
        System.out.println();

        // Application-specific message write in format:
        // userID:vHSMPrivateKeyHandle:Result_PublicKey
        String responseString = "InigoMontoya:6:RESULTINGPUBLICKEYINBASE64";

        if( DEBUG ) {
          System.out.println( "\u001b[30;1m[DRIVER] Writing keypair generation response to file: " + APP_WRITE_FILE + "\u001b[0m");
          System.out.println( "\u001b[30;1m[DRIVER] Response String: " + responseString + "\u001b[0m");
          System.out.println();
        }

        driverWriteToApp( responseString );

        System.out.println( "\u001b[32;1m\u001b[4mResponse returned to application!\u001b[0m" );
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
// driverRead()
//-------|---------|---------|---------|
  public static String driverReadFromApp( ) {
    System.out.println( "This is a read of a message from an application" );
    System.out.println( "Please read me from msgs/application_to_driver.txt" );
    String result = readFileToString( APP_READ_FILE );
    return result;
  }
//-------|---------|---------|---------|
// driverRead()
//-------|---------|---------|---------|
  public static String driverReadFromvHSM( ) {
    System.out.println( "This is a read of a message from a PKCS#11 vHSM" );
    System.out.println( "Please read me from msgs/vHSM_to_driver.txt" );
    String result = readFileToString( VHSM_READ_FILE );
    return result;
  }

//-------|---------|---------|---------|
// driverWrite()
//-------|---------|---------|---------|
  public static void driverWriteToApp( String message ) {
    System.out.println( "This is a write of a message to an application" );
    System.out.println( "Please write me to msgs/driver_to_application.txt" );
    writeStringToFile( message, APP_WRITE_FILE );
  }
//-------|---------|---------|---------|
// driverWrite()
//-------|---------|---------|---------|
  public static void driverWriteTovHSM( String message ) {
    System.out.println( "This is a write of a message to a vHSM" );
    System.out.println( "Please write me to msgs/driver_to_vHSM.txt" );
    writeStringToFile( message, VHSM_WRITE_FILE );
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
    System.out.println( "|   \u001b[1mAR\u001b[0m  - \u001b[4mA\u001b[0mpplication \u001b[4mR\u001b[0mead                                                      |" );
    System.out.println( "|   \u001b[1mVW\u001b[0m  - \u001b[4mV\u001b[0mHSM \u001b[4mW\u001b[0mrite                                                            |" );
    System.out.println( "|   \u001b[1mVR\u001b[0m  - \u001b[4mV\u001b[0mHSM \u001b[4mR\u001b[0mead                                                             |" );
    System.out.println( "|   \u001b[1mAW\u001b[0m  - \u001b[4mA\u001b[0mpplication \u001b[4mW\u001b[0mrite                                                     |" );
    System.out.println( "|   \u001b[1mV\u001b[0m   - \u001b[4mV\u001b[0merbosity (toggle)                                                    |" );
    System.out.println( "|   \u001b[1mX\u001b[0m   - e\u001b[4mX\u001b[0mit                                                                  |" );
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
