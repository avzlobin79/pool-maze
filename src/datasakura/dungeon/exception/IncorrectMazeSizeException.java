
package datasakura.dungeon.exception;

@SuppressWarnings("serial")
public class IncorrectMazeSizeException extends RuntimeException {
	public IncorrectMazeSizeException(String message) {
		super(message);
	}

	public IncorrectMazeSizeException(String message, Throwable cause) {
		super(message, cause);
	}
}