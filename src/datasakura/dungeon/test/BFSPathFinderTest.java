package datasakura.dungeon.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasakura.dungeon.finderpath.BFSPathFinder;
import datasakura.dungeon.finderpath.Coordinate;
import datasakura.dungeon.model.Block;
import datasakura.dungeon.model.Dungeon;
import datasakura.dungeon.model.DungeonPool;

class BFSPathFinderTest {
	private static final Block A = Block.AIR;
	private static final Block G = Block.GROUND;
	static List<Dungeon> dungeons = new ArrayList<>();
	static List<Coordinate> pathExpected = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
		Block [] [] [] blocks={
                
				{  {A,G,G,A,G},
		           {A,G,G,A,G},
		           {A,G,G,G,G},
		           {A,G,G,G,G}
		         },

                {  {A,G,G,G,G},
                   {A,A,A,G,G},
                   {A,G,A,A,G},
                   {G,G,A,G,G}
                }
                };
		
//@formatter:on

		for (int i = 0; i < blocks.length; i++) {

			dungeons.add(new Dungeon(blocks[i]));
		}

		int[][] coordinatesExpected = { { 7, 2 }, { 6, 2 }, { 5, 2 }, { 5, 1 }, { 5, 0 }, { 4, 0 }, { 3, 0 }, { 2, 0 },
				{ 1, 0 }, { 0, 0 } };

		for (int i = 0; i < coordinatesExpected.length; i++) {

			pathExpected.add(new Coordinate(coordinatesExpected[i][1], coordinatesExpected[i][0]));
		}

	}

	@Test
	void testFinderPath() {

		List<Coordinate> pathSolve = new BFSPathFinder().solvePool(new DungeonPool(dungeons), new Coordinate(0, 0));

		assertTrue(equalLists(pathSolve, pathExpected));
	}

	private boolean equalLists(List<Coordinate> one, List<Coordinate> two) {
		if (one == null && two == null) {
			return true;
		}

		if ((one == null && two != null) || one != null && two == null || one.size() != two.size()) {
			return false;
		}

		for (int i = 0; i < one.size(); i++) {

			if (!one.get(i).equals(two.get(i)))
				return false;
		}

		return true;
	}

}


