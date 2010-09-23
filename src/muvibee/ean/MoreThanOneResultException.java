package muvibee.ean;

/**
 *
 * @author Volkan Gökkaya
 */
public class MoreThanOneResultException extends Exception {

    static String fehlerMeldung;

    public MoreThanOneResultException(String fehlermeldung) {
        super(fehlermeldung);
    }
}
