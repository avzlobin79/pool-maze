
package datasakura.dungeon.exception;

@SuppressWarnings("serial")
public class IncorrectIndexPoolException extends RuntimeException {
	public IncorrectIndexPoolException(String message) {
		super(message);
	}

	public IncorrectIndexPoolException(String message, Throwable cause) {
		super(message, cause);
	}
}