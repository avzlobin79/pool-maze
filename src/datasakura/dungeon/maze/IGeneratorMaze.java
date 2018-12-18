package datasakura.dungeon.maze;

import datasakura.dungeon.model.Block;

public interface IGeneratorMaze {

	Block[][] create(int width, int height, int[] rangeEnter);

}
