public class Main {
  public static void main(String[] args) {
    System.out.println( "Hi! I am a PKCS#11 service (vHSM)!" );
    System.out.println( "I want to respond to calls from an application." );
    System.out.println( "I will accept an PKCS#11 compliant request from the driver." );
    applicationCall();
  }

  public void driverRead( ) {
    System.out.println( "This is a read of a message from a driver" );
    System.out.println( "Please read me to msgs/driver_to_vHSM.txt" );
  }

  public void driverWrite( ) {
    System.out.println( "This is an application-specific message being sent to the driver" );
    System.out.println( "Please write me to msgs/vHSM_to_driver.txt" );
  }

}
