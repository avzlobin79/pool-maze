package datasakura.dungeon.utils;

import java.util.Random;

public class RandomGenerator {

	// generation of the range of the set crossing set[start,end]

	public static int[] generateRangeCrossSet(int start, int end, int lengthMax) {

		Random rnd = new Random();

		// common point is belonged both set

		int xCommon = start + rnd.nextInt(end - start + 1);

		// generate left point

		int xLeft = xCommon - rnd.nextInt(xCommon + 1);

		int xRight = xCommon + rnd.nextInt(lengthMax - xCommon);

		int[] result = { xLeft, xRight };

		return result;

	}

	// generate range
	public static int[] generateRange(int lengthMax) {

		Random rnd = new Random();

		// generate start

		int xLeft = rnd.nextInt(lengthMax);

		// generate end

		int xRight = xLeft + rnd.nextInt(lengthMax - xLeft);

		int[] result = { xLeft, xRight };

		return result;

	}

}
