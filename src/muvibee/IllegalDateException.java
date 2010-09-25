package muvibee;

/**
 * Wird bei einem nicht korrekten Datum geworfen.
 * 
 * @author Tim Bartsch
 */
public class IllegalDateException extends Exception{

    public IllegalDateException() {}

    public IllegalDateException(String string) {
        super(string);
    }

}
