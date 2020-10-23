import java.util.Scanner;  // Import the Scanner class

class Main {
  public static void main( String[] args ) {
    System.out.println( args[0] );
    System.out.println( args[1] );

    // Take input
    Scanner myInput = new Scanner( System.in );
    String input = myInput.nextLine();

    // Echo input back out
    System.out.println( input );

    if( input.equals( "asdf" ) ) {
    	System.out.println( "You typed 'asdf'!" );
    }
    else if ( input == "fdsa" ) {
    	System.out.println( "What the what?" );
    }
    else {
    	System.out.println( "The comparison failed!" );
    }

  }
}