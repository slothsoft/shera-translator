package de.slothsoft.shera.dc;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.Word;

/**
 * This class draws not only a single sound, but an entire word.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.2.0
 */

public class WordPainter {

	public static final int PREF_LINE_SIZE = 5;

	private final Canvas canvas;
	private int symbolSize = DrawingContext.PREF_SYMBOL_SIZE;
	private int lineSize = PREF_LINE_SIZE;
	private int padding = 5;

	public WordPainter(Canvas canvas) {
		this.canvas = canvas;
	}

	public WordPainterMetrics paintWords(Word[] words) {
		int maxHeight = 0;
		int entireWidth = 0;

		for (final Word word : words) {
			final PhoneticSound[] wordContent = word.getContent();
			final WordPainterMetrics metrics = paintWord(wordContent);

			maxHeight = Math.max(maxHeight, metrics.getHeight());
			final int widthPlus = metrics.getWidth() + this.padding;
			entireWidth += widthPlus;

			this.canvas.translate(widthPlus, 0);
		}
		return new WordPainterMetrics(entireWidth, maxHeight, 0);
	}

	public WordPainterMetrics paintWord(PhoneticSound[] word) {
		int xTranslation = 0;
		int yTranslation = 0;

		final WordPainterMetrics result = calculateMetrics(word);
		xTranslation += result.getStartX();
		this.canvas.translate(result.getStartX(), 0);

		for (int i = 0; i < word.length; i++) {
			final PhoneticSound sound = word[i];
			final NextDrawing nextDrawing = sound.drawOn(createDrawingContext(this.canvas));
			this.canvas.translate(nextDrawing.startPointX, nextDrawing.startPointY);
			xTranslation += nextDrawing.startPointX;
			yTranslation += nextDrawing.startPointY;

			if (i < word.length - 1 && !nextDrawing.skipConnectionLine) {
				final int lineX = this.symbolSize / 2;
				this.canvas.drawLine(lineX, 0, lineX, this.lineSize);
				this.canvas.translate(0, this.lineSize);
				yTranslation += this.lineSize;
			}
		}
		this.canvas.translate(-xTranslation, -yTranslation);

		return result;
	}

	private DrawingContext createDrawingContext(final Canvas dummyCanvas) {
		return new DrawingContext(dummyCanvas).width(this.symbolSize).height(this.symbolSize);
	}

	public WordPainterMetrics calculateMetrics(PhoneticSound[] word) {
		int height = 0;
		int minX = 0;
		int maxX = 0;
		int currentX = 0;

		final LogCanvas dummyCanvas = new LogCanvas();
		final DrawingContext dc = createDrawingContext(dummyCanvas);

		for (int i = 0; i < word.length; i++) {
			final PhoneticSound sound = word[i];
			final NextDrawing next = sound.drawOn(dc);
			height += next.startPointY;

			// add space for connection line if necessary
			if (!next.skipConnectionLine) {
				height += this.lineSize;
			}
			// shift everything is startPointX is not default
			currentX += next.startPointX;
			minX = Math.min(minX, currentX);
			maxX = Math.max(maxX, currentX);
		}

		return new WordPainterMetrics(maxX - minX + this.symbolSize, height, -minX);
	}

	public int getSymbolSize() {
		return this.symbolSize;
	}

	public WordPainter symbolSize(int newSymbolSize) {
		setSymbolSize(newSymbolSize);
		return this;
	}

	public void setSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
	}

	public int getLineSize() {
		return this.lineSize;
	}

	public WordPainter lineSize(int newLineSize) {
		setLineSize(newLineSize);
		return this;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public int getPadding() {
		return this.padding;
	}

	public WordPainter padding(int newPadding) {
		setPadding(newPadding);
		return this;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

}
