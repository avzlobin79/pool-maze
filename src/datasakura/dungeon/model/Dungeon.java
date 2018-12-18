package datasakura.dungeon.model;

public class Dungeon {

	private final Block[][] area;

	public Dungeon(Block[][] area) {
		super();
		this.area = area;
	}

	public Block[][] getArea() {
		return area;
	}

	@Override
	public String toString() {
		{

			StringBuilder sb = new StringBuilder(area.length);

			for (int i = 0; i < area.length; i++) {

				for (int j = 0; j < area[i].length; j++) {

					sb.append(area[i][j].getSymbol()).append(" ");

				}
				sb.append("\n");

			}

			return sb.append("--BLOCK--").toString();

		}
	}

}
