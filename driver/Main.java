public class Main {
  public static void main(String[] args) {
    System.out.println( "Hi! I am a PKCS#11 compliant driver!" );
    System.out.println( "I shunt requests between an application and compliant vHSM" );

    System.out.println( "When instructed to do so, I will read the application message buffer" );
    applicationRead();
    if( true ) { // TODO: replace with if there was a valid application message
      System.out.println( "Now I will convert the application request to a compliant message to the HSM!" );
      vHSMWrite();
    }

    System.out.println( "Alternately, I can read the vHSM message buffer" );
    vHSMRead();
    if( true ) { // TODO: replace with if there was a valid HSM message
      System.out.println( "Now I will convert the vHSM compliant response to an application message!" );
      applicationWrite();
    }    

  }

  public void applicationRead( ) {
    System.out.println( "This is a read of a message from an application" );
    System.out.println( "Please read me from msgs/application_to_driver.txt" );
  }

  public void applicationWrite( ) {
    System.out.println( "This is a write of a message to an application" );
    System.out.println( "Please write me to msgs/driver_to_application.txt" );
  }

  public void vHSMRead( ) {
    System.out.println( "This is a read of a message from a PKCS#11 vHSM" );
    System.out.println( "Please read me from msgs/vHSM_to_driver.txt" );
  }

  public void vHSMWrite( ) {
    System.out.println( "This is a write of a message to a vHSM" );
    System.out.println( "Please write me to msgs/driver_to_vHSM.txt" );
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
    String result = readFileToString( READ_FILE );
    return result;
  }
//-------|---------|---------|---------|
// driverRead()
//-------|---------|---------|---------|
  public static String driverReadFromvHSM( ) {
    String result = readFileToString( READ_FILE );
    return result;
  }

//-------|---------|---------|---------|
// driverWrite()
//-------|---------|---------|---------|
  public static void driverWriteToApp( String message ) {
    writeStringToFile( message, WRITE_FILE );
  }
//-------|---------|---------|---------|
// driverWrite()
//-------|---------|---------|---------|
  public static void driverWriteTovHSM( String message ) {
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
