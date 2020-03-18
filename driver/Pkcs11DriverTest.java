/**
 * A test class for exercising and verifying the PKCS#11 Driver
 */
public class Pkcs11DriverTest {

	// the driver under test
	private static Pkcs11 driver = new Pkcs11Driver();
	
	public static void main(String[] args) {
		
		// simple test, simple method to just get the ball rolling
		System.out.print( "Checking C_GetInfo Method: " );
		CK_INFO ckInfo = new CK_INFO();
		driver.C_GetInfo( ckInfo );
		if ( ckInfo.libraryVersion != null && ckInfo.libraryVersion.major == Pkcs11Driver.VERSION_MAJOR && ckInfo.libraryVersion.minor == Pkcs11Driver.VERSION_MINOR ) {
			System.out.println("PASS!");
		} else {
			System.out.println("FAIL!");
		}
		
		
		
	}
	
}
