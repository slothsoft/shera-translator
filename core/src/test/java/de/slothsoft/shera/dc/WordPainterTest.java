package de.slothsoft.shera.dc;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.PhoneticSound;

public class WordPainterTest {

	private final LogCanvas canvas = new LogCanvas();
	private final WordPainter painter = new WordPainter(this.canvas);

	@Test
	public void testCalculateMetricsSingle() throws Exception {
		final WordPainterMetrics metrics = this.painter.calculateMetrics(new PhoneticSound[]{PhoneticSound.O});

		Assert.assertNotNull(metrics);
		Assert.assertEquals(DrawingContext.PREF_SYMBOL_SIZE, metrics.width);
		Assert.assertEquals(DrawingContext.PREF_SYMBOL_SIZE, metrics.height);
		Assert.assertEquals(0, metrics.startX);
	}

	@Test
	public void testCalculateMetricsSingleOtherSize() throws Exception {
		final int symbolSize = 10;
		final WordPainterMetrics metrics = this.painter.symbolSize(symbolSize)
				.calculateMetrics(new PhoneticSound[]{PhoneticSound.O});

		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbolSize, metrics.width);
		Assert.assertEquals(symbolSize, metrics.height);
		Assert.assertEquals(0, metrics.startX);
	}

	@Test
	public void testCalculateMetricsTwoWithLine() throws Exception {
		final PhoneticSound connectWithLine = PhoneticSound.H;

		final NextDrawing nextDrawing = connectWithLine.drawOn(new DrawingContext(this.canvas));
		Assert.assertFalse("For the test's sake skipConnectionLine should be false!", nextDrawing.skipConnectionLine);
		Assert.assertEquals("For the test's sake startPointX should be default!", 0, nextDrawing.startPointX);
		Assert.assertEquals("For the test's sake startPointY should be default!", DrawingContext.PREF_SYMBOL_SIZE,
				nextDrawing.startPointY);

		final WordPainterMetrics metrics = this.painter
				.calculateMetrics(new PhoneticSound[]{connectWithLine, PhoneticSound.O});

		final int symbol = DrawingContext.PREF_SYMBOL_SIZE;
		final int line = WordPainter.PREF_LINE_SIZE;

		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbol, metrics.width);
		Assert.assertEquals(2 * symbol + line, metrics.height);
		Assert.assertEquals(0, metrics.startX);
	}

	@Test
	public void testCalculateMetricsTwoWithLineOtherLineSize() throws Exception {
		final PhoneticSound connectWithLine = PhoneticSound.H;

		final NextDrawing nextDrawing = connectWithLine.drawOn(new DrawingContext(this.canvas));
		Assert.assertFalse("For the test's sake skipConnectionLine should be false!", nextDrawing.skipConnectionLine);
		Assert.assertEquals("For the test's sake startPointX should be default!", 0, nextDrawing.startPointX);
		Assert.assertEquals("For the test's sake startPointY should be default!", DrawingContext.PREF_SYMBOL_SIZE,
				nextDrawing.startPointY);

		final int line = 7;
		final WordPainterMetrics metrics = this.painter.lineSize(7)
				.calculateMetrics(new PhoneticSound[]{connectWithLine, PhoneticSound.O});

		final int symbol = DrawingContext.PREF_SYMBOL_SIZE;
		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbol, metrics.width);
		Assert.assertEquals(2 * symbol + line, metrics.height);
		Assert.assertEquals(0, metrics.startX);
	}

	@Test
	public void testCalculateMetricsTwoWithoutLine() throws Exception {
		final PhoneticSound connectDirectly = PhoneticSound.O;

		final NextDrawing nextDrawing = connectDirectly.drawOn(new DrawingContext(this.canvas));
		Assert.assertTrue("For the test's sake skipConnectionLine should be true!", nextDrawing.skipConnectionLine);
		Assert.assertEquals("For the test's sake startPointX should be default!", 0, nextDrawing.startPointX);
		Assert.assertEquals("For the test's sake startPointY should be default!", DrawingContext.PREF_SYMBOL_SIZE,
				nextDrawing.startPointY);

		final WordPainterMetrics metrics = this.painter
				.calculateMetrics(new PhoneticSound[]{connectDirectly, PhoneticSound.O});

		final int symbol = DrawingContext.PREF_SYMBOL_SIZE;

		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbol, metrics.width);
		Assert.assertEquals(2 * symbol, metrics.height);
		Assert.assertEquals(0, metrics.startX);
	}

	@Test
	public void testCalculateMetricsTwoWithMovedLeft() throws Exception {
		final PhoneticSound movedLeft = PhoneticSound.A;

		final NextDrawing nextDrawing = movedLeft.drawOn(new DrawingContext(this.canvas));
		Assert.assertTrue("For the test's sake skipConnectionLine should be true!", nextDrawing.skipConnectionLine);
		Assert.assertNotEquals("For the test's sake startPointX should NOT be default!", 0, nextDrawing.startPointX);
		Assert.assertEquals("For the test's sake startPointY should be default!", DrawingContext.PREF_SYMBOL_SIZE,
				nextDrawing.startPointY);

		final WordPainterMetrics metrics = this.painter
				.calculateMetrics(new PhoneticSound[]{movedLeft, PhoneticSound.O});

		final int offset = Math.abs(nextDrawing.startPointX);
		final int symbol = DrawingContext.PREF_SYMBOL_SIZE;

		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbol + offset, metrics.width);
		Assert.assertEquals(2 * symbol, metrics.height);
		Assert.assertEquals(offset, metrics.startX);
	}

	@Test
	public void testCalculateMetricsTwoWithMovedLeftTwice() throws Exception {
		final PhoneticSound movedLeft = PhoneticSound.A;

		final NextDrawing nextDrawing = movedLeft.drawOn(new DrawingContext(this.canvas));
		Assert.assertTrue("For the test's sake skipConnectionLine should be true!", nextDrawing.skipConnectionLine);
		Assert.assertNotEquals("For the test's sake startPointX should NOT be default!", 0, nextDrawing.startPointX);
		Assert.assertEquals("For the test's sake startPointY should be default!", DrawingContext.PREF_SYMBOL_SIZE,
				nextDrawing.startPointY);

		final WordPainterMetrics metrics = this.painter
				.calculateMetrics(new PhoneticSound[]{movedLeft, movedLeft, PhoneticSound.O});

		final int offset = Math.abs(nextDrawing.startPointX);
		final int symbol = DrawingContext.PREF_SYMBOL_SIZE;

		Assert.assertNotNull(metrics);
		Assert.assertEquals(symbol + 2 * offset, metrics.width);
		Assert.assertEquals(3 * symbol, metrics.height);
		Assert.assertEquals(2 * offset, metrics.startX);
	}
}
