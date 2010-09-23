package muvibee.ean;

/**
 *
 * @author Volkan Gökkaya
 */
public class NoResultException extends Exception {

    static String fehlerMeldung;

    public NoResultException(String fehlermeldung) {
        super(fehlermeldung);
    }
}
