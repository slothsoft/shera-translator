package de.slothsoft.shera.dc;

import java.util.Objects;

/**
 * Collects all configurations for a {@link WordPainter}.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.4.0
 */

public class WordPainterConfig {

	public enum WritingSystem {
		LEFT_TO_RIGHT(1),

		RIGHT_TO_LEFT(-1),

		;

		int moveDirection; // 1 for right, -1 for left

		private WritingSystem(int moveDirection) {
			this.moveDirection = moveDirection;
		}

		int getInitialOffset(Canvas canvas) {
			return this == LEFT_TO_RIGHT ? 0 : canvas.getWidth();
		}

		int getWordOffset(WordPainterMetrics wordSize) {
			return this == LEFT_TO_RIGHT ? 0 : -wordSize.getWidth();
		}

	}

	int symbolSize = DrawingContext.PREF_SYMBOL_SIZE;
	int lineSize = WordPainter.PREF_LINE_SIZE;
	int padding = 5;
	WritingSystem writingSystem = WritingSystem.RIGHT_TO_LEFT;

	public int getSymbolSize() {
		return this.symbolSize;
	}

	public WordPainterConfig symbolSize(int newSymbolSize) {
		setSymbolSize(newSymbolSize);
		return this;
	}

	public void setSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
	}

	public int getLineSize() {
		return this.lineSize;
	}

	public WordPainterConfig lineSize(int newLineSize) {
		setLineSize(newLineSize);
		return this;
	}

	public void setLineSize(int lineSize) {
		this.lineSize = lineSize;
	}

	public int getPadding() {
		return this.padding;
	}

	public WordPainterConfig padding(int newPadding) {
		setPadding(newPadding);
		return this;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public WritingSystem getWritingSystem() {
		return this.writingSystem;
	}

	public WordPainterConfig writingSystem(WritingSystem newWritingSystem) {
		setWritingSystem(newWritingSystem);
		return this;
	}

	public void setWritingSystem(WritingSystem writingSystem) {
		Objects.requireNonNull(writingSystem);
		this.writingSystem = writingSystem;
	}

}
