package datasakura.dungeon.test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import datasakura.dungeon.exception.EmptyPoolMazeException;
import datasakura.dungeon.exception.EqualsBlockPoolMazeException;
import datasakura.dungeon.exception.NoEntrancePoolMazeException;
import datasakura.dungeon.exception.NoWayPoolMazeException;
import datasakura.dungeon.model.Block;
import datasakura.dungeon.model.Dungeon;
import datasakura.dungeon.model.DungeonPool;

class TestDungeonPool {

	private static final Block A = Block.AIR;
	private static final Block G = Block.GROUND;
	static List<Dungeon> dungeonsCorrect = new ArrayList<>();
	static List<Dungeon> dungeonsIncorrect1 = new ArrayList<>();
	static List<Dungeon> dungeonsIncorrect2 = new ArrayList<>();
	static List<Dungeon> dungeonsIncorrect3 = new ArrayList<>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		// Set Block Maze for testing
		// correctMaze
		// @formatter:on
 
	Block [] [] [] blocksCorrect={
		                 { {A,G,G,A,G},
				           {A,A,G,A,G},
				           {A,G,G,G,G},
				           {A,A,G,G,G}
				         },
		
		                 { {A,G,G,G,G},
	                       {A,A,G,G,G},
	                       {A,G,G,A,G},
	                       {A,G,G,G,G}
	                     },
		
		                 { {A,G,G,G,G},
	                       {A,A,G,G,G},
	                       {A,A,A,A,A},
	                       {A,A,G,G,A}
	                      }
	                     };
		// @formatter:on

		for (int i = 0; i < blocksCorrect.length; i++) {

			dungeonsCorrect.add(new Dungeon(blocksCorrect[i]));

		}

		// No Way

		// @formatter:off
		
		Block [] [] [] blocksIncorrect1={
                {  {A,G,G,A,G},
		           {A,A,G,A,G},
		           {A,G,G,G,G},
		           {A,A,G,G,G}
		         },

                { {A,G,G,G,G},
                  {A,A,G,G,G},
                  {A,G,G,A,G},
                  {A,G,G,G,G}
                },

                { {A,G,G,G,G},
                  {A,A,G,G,G},
                  {A,A,A,G,G},
                  {G,G,G,A,G}
                 }
                };
		// @formatter:on

		for (int i = 0; i < blocksIncorrect1.length; i++) {

			dungeonsIncorrect1.add(new Dungeon(blocksIncorrect1[i]));

		}

		// No Entrance

		// @formatter:off

Block [] [] [] blocksIncorrect2={
        {  {G,G,G,G,G},
           {A,A,G,A,G},
           {A,G,G,G,G},
           {A,A,G,G,G}
         },

        { {A,G,G,G,G},
          {A,A,G,G,G},
          {A,G,G,A,G},
          {A,G,G,G,G}
        },

        { {A,G,G,G,G},
          {A,A,G,G,G},
          {A,A,A,G,G},
          {G,G,A,A,G}
         }
        };
		// @formatter:on

		for (int i = 0; i < blocksIncorrect2.length; i++) {

			dungeonsIncorrect2.add(new Dungeon(blocksIncorrect2[i]));
		}

		// Is equals neighbor blocks

		// @formatter:off	

Block [] [] [] blocksIncorrect3={
     
	  { {A,G,G,G,G},
        {A,A,G,A,G},
        {A,G,G,G,G},
        {A,A,G,G,G}
      },

     { 
    	{A,G,G,G,G},
        {A,A,G,A,G},
        {A,G,G,G,G},
        {A,A,G,G,G}
     },

     { {A,G,G,G,G},
       {A,A,G,G,G},
       {A,A,A,G,G},
       {G,G,A,A,G}
      }
     };
		// @formatter:on

		for (int i = 0; i < blocksIncorrect3.length; i++) {

			dungeonsIncorrect3.add(new Dungeon(blocksIncorrect3[i]));

		}

}

	// correct
	@Test
	void testCorrectConstructorDungeonPool() {

		DungeonPool poolDungeon = new DungeonPool(dungeonsCorrect);

		assertEquals(poolDungeon.getSize(), 3);
	}

	// incorrect

	@Test
	void testConstructorNoWayPoolMazeException() {

		assertThrows(NoWayPoolMazeException.class, () -> {
			new DungeonPool(dungeonsIncorrect1);
		});
	}

	@Test
	void testConstructorNoEntrancePoolMazeException() {

		assertThrows(NoEntrancePoolMazeException.class, () -> {
			new DungeonPool(dungeonsIncorrect2);
		});

	}

	@Test
	void testConstructorDungeonPoolEqualsException() {

		assertThrows(EqualsBlockPoolMazeException.class, () -> {
			new DungeonPool(dungeonsIncorrect3);
		});

	}

	@Test
	void testConstructorEmptyPoolMazeException() {

		assertThrows(EmptyPoolMazeException.class, () -> {
			new DungeonPool(Collections.emptyList());
		});

	}

	// generate Maze and check using his constructor
	@Test
	void testGenerateMaze() {

		DungeonPool poolDungeon = new DungeonPool();

		List<Dungeon> dungeons = poolDungeon.createXSequence(3);

		poolDungeon.print();

		assertEquals(new DungeonPool(dungeons).getSize(), 3);

	}

}
