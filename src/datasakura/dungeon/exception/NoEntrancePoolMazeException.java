
package datasakura.dungeon.exception;

@SuppressWarnings("serial")
public class NoEntrancePoolMazeException extends RuntimeException {
	public NoEntrancePoolMazeException(String message) {
		super(message);
	}

	public NoEntrancePoolMazeException(String message, Throwable cause) {
		super(message, cause);
	}
}