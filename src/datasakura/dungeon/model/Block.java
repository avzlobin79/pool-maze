package datasakura.dungeon.model;

public enum Block {

	GROUND("X"), AIR("O");

	private final String symbol;

	Block(String symbol) {

		this.symbol = symbol;

	}

	public String getSymbol() {
		return symbol;
	}

}
