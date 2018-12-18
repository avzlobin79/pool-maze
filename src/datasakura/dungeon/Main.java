package datasakura.dungeon;

import java.util.List;

import datasakura.dungeon.finderpath.BFSPathFinder;
import datasakura.dungeon.finderpath.Coordinate;
import datasakura.dungeon.model.DungeonPool;

public class Main {

	public static void main(String[] args) {

		DungeonPool pool = new DungeonPool();

		pool.createXSequence(2);

		BFSPathFinder solverPATH = new BFSPathFinder();

		List<Coordinate> path = solverPATH.solvePool(pool, new Coordinate(1, 0));

		if (path.isEmpty())
			System.out.println("No way for maze from 1,0");
		path.forEach(System.out::println);
		pool.print();
	}
}
