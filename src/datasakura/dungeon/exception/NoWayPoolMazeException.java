
package datasakura.dungeon.exception;

@SuppressWarnings("serial")
public class NoWayPoolMazeException extends RuntimeException {
	public NoWayPoolMazeException(String message) {
		super(message);
	}

	public NoWayPoolMazeException(String message, Throwable cause) {
		super(message, cause);
	}
}