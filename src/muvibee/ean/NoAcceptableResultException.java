package muvibee.ean;

class NoAcceptableResultException extends Exception {

	public NoAcceptableResultException(String fehlermeldung) {
		super(fehlermeldung);
	}
}
