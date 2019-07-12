package de.slothsoft.shera;

import org.junit.Assert;
import org.junit.Test;

public class LineTest {

	private static final double DELTA = 0.01;

	@Test
	public void testGetYWithIntercept() throws Exception {
		final Line line = new Line().slope(1).intercept(2);

		Assert.assertEquals(2, line.calculateY(0), DELTA);
		Assert.assertEquals(3, line.calculateY(1), DELTA);
		Assert.assertEquals(4, line.calculateY(2), DELTA);
		Assert.assertEquals(5, line.calculateY(3), DELTA);
	}

	@Test
	public void testGetYWithSlope() throws Exception {
		final Line line = new Line().slope(3).intercept(0);

		Assert.assertEquals(0, line.calculateY(0), DELTA);
		Assert.assertEquals(3, line.calculateY(1), DELTA);
		Assert.assertEquals(6, line.calculateY(2), DELTA);
		Assert.assertEquals(9, line.calculateY(3), DELTA);
	}

	@Test
	public void testGetYWithSlopeAndIntercept() throws Exception {
		final Line line = new Line().slope(2).intercept(3);

		Assert.assertEquals(3, line.calculateY(0), DELTA);
		Assert.assertEquals(5, line.calculateY(1), DELTA);
		Assert.assertEquals(7, line.calculateY(2), DELTA);
		Assert.assertEquals(9, line.calculateY(3), DELTA);
	}

	@Test
	public void testThroughWithSlope() throws Exception {
		final Line line = Line.through(0, 0, 1, 2);

		Assert.assertNotNull(line);
		Assert.assertEquals(2, line.getSlope(), DELTA);
		Assert.assertEquals(0, line.getIntercept(), DELTA);
	}

	@Test
	public void testThroughWithIntercept() throws Exception {
		final Line line = Line.through(0, -2, 1, -1);

		Assert.assertNotNull(line);
		Assert.assertEquals(1, line.getSlope(), DELTA);
		Assert.assertEquals(-2, line.getIntercept(), DELTA);
	}

	@Test
	public void testThroughWithSlopeAndIntercept() throws Exception {
		final Line line = Line.through(1, 5, 3, 9);

		Assert.assertNotNull(line);
		Assert.assertEquals(2, line.getSlope(), DELTA);
		Assert.assertEquals(3, line.getIntercept(), DELTA);
	}

	@Test
	public void testThroughHorizontal() throws Exception {
		final Line line = Line.through(0, 1, 2, 1);

		Assert.assertNotNull(line);
		Assert.assertEquals(0, line.getSlope(), DELTA);
		Assert.assertEquals(1, line.getIntercept(), DELTA);
	}
}
