package datasakura.dungeon.finderpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import datasakura.dungeon.model.Block;
import datasakura.dungeon.model.Dungeon;
import datasakura.dungeon.model.DungeonPool;

public class BFSPathFinder {

	private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

	private final Set<Coordinate> mapVisited = new HashSet<>();

	public List<Coordinate> solvePool(DungeonPool poolMaze, Coordinate start)

	{

		List<Block[][]> list = poolMaze.getStream().map(x -> x.getArea()).collect(Collectors.toList());

		Block[][] mergeBlocks = mergeBlocks(list);

		return solve(mergeBlocks, start);

	}

	public List<Coordinate> solvePool(List<Dungeon> poolMaze, Coordinate start)

	{

		List<Block[][]> list = poolMaze.stream().map(x -> x.getArea()).collect(Collectors.toList());

		Block[][] mergeBlocks = mergeBlocks(list);

		return solve(mergeBlocks, start);

	}

	public List<Coordinate> solve(Dungeon maze, Coordinate start) {

		return solve(maze.getArea(), start);

	}

	private List<Coordinate> solve(Block[][] area, Coordinate start) {

		LinkedList<Coordinate> nextToVisit = new LinkedList<>();

		nextToVisit.add(start);

		while (!nextToVisit.isEmpty()) {
			Coordinate cur = nextToVisit.remove();

			if (!isValidate(cur.getX(), cur.getY(), area) || isExplored(cur.getX(), cur.getY())) {
				continue;

			}

			if (isGround(cur.getX(), cur.getY(), area)) {
				setVisited(cur.getX(), cur.getY(), true);
				continue;
			}

			if (isExit(cur.getX(), cur.getY(), area)) {
				return backtrackPath(cur);
			}

			for (int[] direction : DIRECTIONS) {
				Coordinate coordinate = new Coordinate(cur.getX() + direction[0], cur.getY() + direction[1], cur);
				nextToVisit.add(coordinate);
				setVisited(cur.getX(), cur.getY(), true);
			}
		}
		return Collections.emptyList();
	}

	private Boolean isExplored(int x, int y) {

		return mapVisited.contains(new Coordinate(x, y));

	}

	private boolean isValidate(int x, int y, Block[][] area) {

		if (y < 0 || y > area.length - 1 || x < 0 || x > area[0].length - 1) {

			return false;
		}

		return true;

	}

	private boolean isGround(int x, int y, Block[][] area) {

		if (area[y][x] == Block.GROUND)
			return true;

		return false;

	}

	private boolean isExit(int x, int y, Block[][] area) {

		if ((area[y][x] == Block.AIR) && y == area.length - 1)
			return true;

		return false;

	}

	private void setVisited(int x, int y, boolean value) {

		mapVisited.add(new Coordinate(x, y));

	}

	private List<Coordinate> backtrackPath(Coordinate cur) {
		List<Coordinate> path = new ArrayList<>();
		Coordinate iter = cur;

		while (iter != null) {
			path.add(iter);
			iter = iter.parent;
		}

		return path;
	}

	private Block[][] mergeBlocks(List<Block[][]> list) {

		int width = list.get(0)[0].length;

		int height = list.get(0).length * list.size();

		Block[][] result = new Block[height][width];

		int counter = 0;

		for (int i = 0; i < list.size(); i++)

		{
			for (int y = 0; y < list.get(i).length; y++) {

				for (int x = 0; x < list.get(i)[0].length; x++) {

					result[counter][x] = (list.get(i))[y][x];
				}

				counter++;
			}
		}

		return result;
	}

}