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
}
