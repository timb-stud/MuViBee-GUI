package muvibee.ean;

/**
 *
 * @author Volkan Gökkaya
 */
public class NoResultException extends Exception {

    public NoResultException(String fehlermeldung) {
        super(fehlermeldung);
    }
}
