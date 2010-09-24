package muvibee.ean;

/**
 *
 * @author Volkan Gökkaya
 */
public class MoreThanOneResultException extends Exception {

    public MoreThanOneResultException(String fehlermeldung) {
        super(fehlermeldung);
    }
}
