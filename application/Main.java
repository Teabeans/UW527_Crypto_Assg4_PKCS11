public class Main {
  public static void main(String[] args) {
    System.out.println( "Hi! I am a host/application!" );
    System.out.println( "I want to make a call to the HSM." );
    System.out.println( "I will send an application-specific request to the driver." );
    driverWrite();

    System.out.println( "I can also read application-specific responses from the driver." );
    driverRead();
  }

  public void driverRead( ) {
    System.out.println( "This is a read of a message from a driver" );
    System.out.println( "Please read me to msgs/driver_to_application.txt" );
  }

  public void driverWrite( ) {
  	System.out.println( "This is an application-specific message being sent to the driver" );
  	System.out.println( "Please write me to msgs/application_to_driver.txt" );
  }
}
