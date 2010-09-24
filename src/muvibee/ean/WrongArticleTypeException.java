package muvibee.ean;

/**
 *
 * @author Volkan Gökkaya
 */
public class WrongArticleTypeException extends Exception {

    public WrongArticleTypeException(String fehlermeldung) {
        super(fehlermeldung);
    }
}
