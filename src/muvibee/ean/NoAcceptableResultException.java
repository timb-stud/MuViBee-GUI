package muvibee.ean;

public class NoAcceptableResultException extends Exception {

    static String fehlerMeldung;

    public NoAcceptableResultException(String fehlermeldung) {
        super(fehlermeldung);
        this.fehlerMeldung = fehlermeldung;
    }

    public static String getFehlerMeldung() {
        return fehlerMeldung;
    }
}
