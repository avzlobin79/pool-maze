package datasakura.dungeon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import datasakura.dungeon.exception.EmptyPoolMazeException;
import datasakura.dungeon.exception.EqualsBlockPoolMazeException;
import datasakura.dungeon.exception.IncorrectIndexPoolException;
import datasakura.dungeon.exception.NoEntrancePoolMazeException;
import datasakura.dungeon.exception.NoWayPoolMazeException;
import datasakura.dungeon.finderpath.BFSPathFinder;
import datasakura.dungeon.finderpath.Coordinate;
import datasakura.dungeon.maze.MyGeneratorMazeImpl;
import datasakura.dungeon.utils.RandomGenerator;

public class DungeonPool {

	private static final int WIDTH_BLOCK_MAZE_GENERATE = 5;

	private static final int HEIGHT_BLOCK_MAZE_GENERATE = 4;

	private List<Dungeon> dungeons = new LinkedList<>();

	private final MyGeneratorMazeImpl generatorMaze = new MyGeneratorMazeImpl();

	private final BFSPathFinder solverPath = new BFSPathFinder();

	public DungeonPool(List<Dungeon> dungeons) {
		super();

		if (dungeons.isEmpty())
			throw new EmptyPoolMazeException("Pool maze is empty!");

		int indexEqualsBlock = checkEqualsMaze(dungeons);

		if (indexEqualsBlock > -1)
			throw new EqualsBlockPoolMazeException("Equals block PoolMaze!" + "Block:" + (indexEqualsBlock - 1) + "=="
					+ "Block:" + (indexEqualsBlock));

		// check pathless for each entrance if one of path is correct it is mean that

		List<Coordinate> entrances = getCorrdinatesEntrance(dungeons);

		if (entrances.isEmpty())
			throw new NoEntrancePoolMazeException("No entrance for PoolMaze!");

		if (!checkPathMazePool(entrances, dungeons))
			throw new NoWayPoolMazeException("No way for pool maze!");
		// maze is correct

		this.dungeons = dungeons;
	}

	public DungeonPool() {

	}

	public List<Dungeon> createXSequence(int length) {

		int[] rangeExit = null;

		for (int i = 0; i < length; i++) {

			// first block maze generate with random entrances
			if (i == 0) {

				Block[][] blockMazeFirst = generatorMaze.create(WIDTH_BLOCK_MAZE_GENERATE, HEIGHT_BLOCK_MAZE_GENERATE,
						RandomGenerator.generateRange(WIDTH_BLOCK_MAZE_GENERATE));
				rangeExit = generatorMaze.getRangeExitLastGeneration();

				Dungeon dungeonFirst = new Dungeon(blockMazeFirst);

				dungeons.add(dungeonFirst);

				continue;

			}

			Block[][] blockMaze = generatorMaze.create(WIDTH_BLOCK_MAZE_GENERATE, HEIGHT_BLOCK_MAZE_GENERATE,
					rangeExit);

			// check equals Block if equals repeat again
			if (Arrays.deepEquals(dungeons.get(i - 1).getArea(), blockMaze)) {

				--i;

				continue;

			}

			Dungeon dungeon = new Dungeon(blockMaze);

			dungeons.add(dungeon);

		}

		return dungeons;

	}

	public Dungeon getDungeon(int index) {

		if (dungeons.isEmpty())
			throw new EmptyPoolMazeException("Pool maze is empty!");

		if (index < dungeons.size())
			return dungeons.get(index);

		throw new IncorrectIndexPoolException(
				"Incorrect index of pool maze!" + "Index must be less than " + dungeons.size());

	}

	public int getSize() {

		return dungeons.size();

	}

	public Stream<Dungeon> getStream() {

		return dungeons.stream();

	}

	public void print() {

		dungeons.forEach(System.out::println);

	}

	private boolean checkPathMazePool(List<Coordinate> entrances, List<Dungeon> dungeons) {

		for (int i = 0; i < entrances.size(); i++) {

			if (!solverPath.solvePool(dungeons, entrances.get(i)).isEmpty())
				return true;
		}

		return false;

	}

	// check equals with prev bloc return number blocks
	private int checkEqualsMaze(List<Dungeon> dungeons) {

		for (int i = 1; i < dungeons.size(); i++) {
			Block[][] blockPrev = dungeons.get(i - 1).getArea();

			Block[][] blockCurrent = dungeons.get(i).getArea();

			if (Arrays.deepEquals(blockPrev, blockCurrent)) {

				return i;

			}

		}

		return -1;

	}

	private List<Coordinate> getCorrdinatesEntrance(List<Dungeon> dungeons) {

		List<Coordinate> result = new ArrayList<>();

		Block[] entrance = dungeons.get(0).getArea()[0];

		for (int i = 0; i < entrance.length; i++) {

			if (entrance[i] == Block.AIR)
				result.add(new Coordinate(i, 0));
		}

		return result;

	}

}
