package datasakura.dungeon.maze;

import datasakura.dungeon.exception.IncorrectMazeSizeException;
import datasakura.dungeon.model.Block;
import datasakura.dungeon.utils.RandomGenerator;

public class MyGeneratorMazeImpl implements IGeneratorMaze {

	private int[] rangeExitLastGeneration;

	@Override
	public Block[][] create(int width, int height, int[] rangeEntrance) {

		Block[][] result = new Block[height][width];

		// checking size maze
		if (width < 2 || height < 2)
			throw new IncorrectMazeSizeException("Incorrect size of maze!" + "Size must be greater than 1");

		int[] rangeRow = rangeEntrance;

		for (int y = 0; y < height; y++) {

			if (y > 0) {

				rangeRow = RandomGenerator.generateRangeCrossSet(rangeRow[0], rangeRow[1], width);
			}
			// write exits range
			if (y == height - 1) {

				rangeExitLastGeneration = rangeRow;
			}

			for (int x = 0; x < width; x++) {

				if ((x >= rangeRow[0]) && (x <= rangeRow[1]))

				{
					result[y][x] = Block.AIR;

				} else {

					result[y][x] = Block.GROUND;

				}

			}

		}

		return result;
	}

	public int[] getRangeExitLastGeneration() {
		return rangeExitLastGeneration;
	}

}
