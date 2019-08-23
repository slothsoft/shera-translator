package de.slothsoft.shera.dc;

import java.util.Objects;

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

	private WordPainterConfig config = new WordPainterConfig();

	public WordPainter(Canvas canvas) {
		this.canvas = canvas;
	}

	public WordPainterMetrics paintWords(Word[] words) {
		int maxHeight = 0;
		int entireWidth = 0;

		final int initialOffset = this.config.writingSystem.getInitialOffset(this.canvas);
		this.canvas.translate(initialOffset, 0);

		for (final Word word : words) {
			final PhoneticSound[] wordContent = word.getContent();
			final WordPainterMetrics metrics = paintWord(wordContent);

			maxHeight = Math.max(maxHeight, metrics.getHeight());
			final int widthPlus = metrics.getWidth() + this.config.padding;
			entireWidth += widthPlus;

			this.canvas.translate(this.config.writingSystem.moveDirection * widthPlus, 0);
		}
		this.canvas.translate(-initialOffset, 0);

		return new WordPainterMetrics(entireWidth, maxHeight, 0);
	}

	public WordPainterMetrics paintWord(PhoneticSound[] word) {
		int xTranslation = 0;
		int yTranslation = 0;

		final WordPainterMetrics result = calculateMetrics(word);

		final int startX = result.getStartX();
		final int wordOffset = this.config.writingSystem.getWordOffset(result);
		xTranslation += startX + wordOffset;
		this.canvas.translate(xTranslation, 0);

		for (int i = 0; i < word.length; i++) {
			final PhoneticSound sound = word[i];
			final NextDrawing nextDrawing = sound.drawOn(createDrawingContext(this.canvas));
			this.canvas.translate(nextDrawing.startPointX, nextDrawing.startPointY);
			xTranslation += nextDrawing.startPointX;
			yTranslation += nextDrawing.startPointY;

			if (i < word.length - 1 && !nextDrawing.skipConnectionLine) {
				final int lineX = this.config.symbolSize / 2;
				this.canvas.drawLine(lineX, 0, lineX, this.config.lineSize);
				this.canvas.translate(0, this.config.lineSize);
				yTranslation += this.config.lineSize;
			}
		}
		this.canvas.translate(-xTranslation, -yTranslation);

		return result;
	}

	private DrawingContext createDrawingContext(final Canvas dummyCanvas) {
		return new DrawingContext(dummyCanvas).width(this.config.symbolSize).height(this.config.symbolSize);
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
				height += this.config.lineSize;
			}
			// shift everything is startPointX is not default
			currentX += next.startPointX;
			minX = Math.min(minX, currentX);
			maxX = Math.max(maxX, currentX);
		}

		return new WordPainterMetrics(maxX - minX + this.config.symbolSize, height, -minX);
	}

	public int getSymbolSize() {
		return this.config.getSymbolSize();
	}

	public WordPainter symbolSize(int newSymbolSize) {
		setSymbolSize(newSymbolSize);
		return this;
	}

	public void setSymbolSize(int symbolSize) {
		this.config.setSymbolSize(symbolSize);;
	}

	public int getLineSize() {
		return this.config.getLineSize();
	}

	public WordPainter lineSize(int newLineSize) {
		setLineSize(newLineSize);
		return this;
	}

	public void setLineSize(int lineSize) {
		this.config.setLineSize(lineSize);
	}

	public int getPadding() {
		return this.config.getPadding();
	}

	public WordPainter padding(int newPadding) {
		setPadding(newPadding);
		return this;
	}

	public void setPadding(int padding) {
		this.config.setPadding(padding);
	}

	public WordPainterConfig getConfig() {
		return this.config;
	}

	public WordPainter config(WordPainterConfig newConfig) {
		setConfig(newConfig);
		return this;
	}

	public void setConfig(WordPainterConfig config) {
		Objects.requireNonNull(config);
		this.config = config;
	}

}
