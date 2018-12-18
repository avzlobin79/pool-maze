
package datasakura.dungeon.exception;

@SuppressWarnings("serial")
public class EmptyPoolMazeException extends RuntimeException {
	public EmptyPoolMazeException(String message) {
		super(message);
	}

	public EmptyPoolMazeException(String message, Throwable cause) {
		super(message, cause);
	}
}