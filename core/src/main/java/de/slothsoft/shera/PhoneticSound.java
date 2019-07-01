package de.slothsoft.shera;

import de.slothsoft.shera.dc.Canvas;
import de.slothsoft.shera.dc.DrawingContext;
import de.slothsoft.shera.dc.NextDrawing;

/**
 * All the phonetic sounds the first ones writing has. See <a href=
 * "http://www.dreamworkstv.com/wp-content/uploads/2015/07/SheRa_FirstOnes_Language.pdf">Dreamworks
 * handy guide</a>. This class should only contain official information about the sounds
 * and symbols.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public enum PhoneticSound implements Constants {

	A("SAD", true, false, false) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int offset = (int) (SMALL_BALL_RATIO * width);
			final int y = height / 2;
			final int endPoint = width / 2 - offset;

			canvas.drawLine(width / 2, 0, width / 2, y);
			canvas.drawLine(width / 2, y, width / 2 - offset, y);
			canvas.drawLine(width / 2 - offset, y, endPoint, height);

			result.setStartPointX(endPoint - width / 2);
			result.setSkipConnectionLine(true);
		}

	},

	AH("ALL", true, false, false) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int offsetX = (int) (SMALL_BALL_RATIO * width);
			final int offsetY = (int) (SMALL_BALL_RATIO * height);
			final int y = (height - offsetY) / 2;
			final int endPoint = width / 2 - offsetX;

			canvas.drawLine(width / 2, 0, width / 2, y);
			canvas.drawLine(width / 2, y, width / 2 - offsetX, y + offsetY);
			canvas.drawLine(width / 2 - offsetX, y + offsetY, endPoint, height);

			result.setStartPointX(endPoint - width / 2);
			result.setSkipConnectionLine(true);
		}

	},

	AY("SAY", true, false, false) { // SEE AI, BOY

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, oneThirdHeight);
			canvas.drawLine(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2, halfHeight);
			canvas.drawLine(halfWidth + halfWidth / 2, halfHeight, halfWidth, 2 * oneThirdHeight);
			canvas.drawLine(halfWidth, 2 * oneThirdHeight, halfWidth, height);
		}

	},

	B(null, false, true, true) { // see M, P

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			P.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	CH(null, false, false, false) { // see J

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			canvas.fillTriangle(halfWidth, 0, halfWidth, height, -halfWidth / 2, height);
			canvas.drawTriangle(halfWidth, 0, halfWidth, height, -halfWidth / 2, height);
			result.setStartPointX(-3 * halfWidth / 4); // looks centerish
		}

	},

	D(null, false, true, true) { // see H, T

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.fillRectangle(0, 0, width, height);
			canvas.drawRectangle(0, 0, width, height);
			drawCenterPointOn(canvas, width, height);
		}

	},

	DH(null, false, true, true) { // see F, V

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			F.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			final int oneThirdWidth = width / 3;
			canvas.drawLine(oneThirdWidth, 0, halfWidth, height);
			canvas.drawLine(2 * oneThirdWidth, 0, halfWidth, height);
		}

	},

	E("PET", false, true, false) { // see TOO

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			final int separator = height / 10;

			canvas.fillOval(ballWidth, 0, width - ballWidth, ballHeight);
			canvas.drawOval(ballWidth, 0, width - ballWidth, ballHeight);
			canvas.drawLine(width / 2, ballHeight, width / 2, ballHeight + separator);
			canvas.fillOval(ballWidth, ballHeight + separator, width - ballWidth, 2 * ballHeight + separator);
			canvas.drawOval(ballWidth, ballHeight + separator, width - ballWidth, 2 * ballHeight + separator);

			result.startPointY(2 * ballHeight + separator);
		}

	},

	EE("FEET", false, true, true) { // see N, GO

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			canvas.fillOval((width - ballWidth) / 2, 0, (width + ballWidth) / 2, ballHeight);
			canvas.drawOval((width - ballWidth) / 2, 0, (width + ballWidth) / 2, ballHeight);

			result.setStartPointY(ballHeight);
		}

	},

	F(null, false, true, true) { // see DH, V

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			canvas.drawLine(0, 0, width, 0);
			canvas.drawLine(width, 0, halfWidth, height);
			canvas.drawLine(halfWidth, height, 0, 0);
		}

	},

	G(null, false, true, true) { // see K

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			K.drawOn(canvas, width, height, result);

			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * height);
			final int x = (width - ballWidth) / 2;
			final int y = 2 * height / 3 - ballHeight / 2;
			canvas.fillOvalWithForeground(x, y, x + ballWidth, y + ballHeight);
		}

	},

	H(null, false, true, true) { // see T, D

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.fillRectangleWithForeground(0, 0, width, height);
		}

	},

	I("LIT", false, true, true) { // see S

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int startX = width / 2;
			canvas.fillTriangleWithForeground(startX, 0, width, height, 0, height);
		}

	},

	AI("I", true, false, false) { // see SAY

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, height);
			canvas.fillTriangleWithForeground(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2, halfHeight,
					halfWidth, 2 * oneThirdHeight);
		}

	},

	J(null, false, false, false) { // see CH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			CH.drawOn(canvas, width, height, result);

			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * width);
			final int x = width / 8;// looks centerish
			final int y = height - 2 * ballHeight;
			canvas.fillOvalWithForeground(x, y, x + ballWidth, y + ballHeight);
		}

	},

	K(null, false, true, true) { // see G

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = 2 * height / 3;
			canvas.drawLine(halfWidth, 0, width, halfHeight);
			canvas.drawLine(width, halfHeight, halfWidth, height);
			canvas.drawLine(halfWidth, height, 0, halfHeight);
			canvas.drawLine(0, halfHeight, halfWidth, 0);
		}

	},

	L(null, false, true, true) { // see R

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int quarterWidth = width / 4;
			canvas.drawLine(quarterWidth, 0, width, 0);
			canvas.drawLine(width, 0, width - quarterWidth, height);
			canvas.drawLine(width - quarterWidth, height, 0, height);
			canvas.drawLine(0, height, quarterWidth, 0);
		}

	},

	M(null, false, true, true) { // see B, P

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.fillTriangleWithForeground(halfWidth, 0, width, halfHeight, 0, halfWidth);
			canvas.fillTriangleWithForeground(halfWidth, height, 0, halfWidth, width, halfHeight);
		}

	},

	N(null, false, true, true) { // see FEET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * height);
			canvas.fillOvalWithForeground((width - ballWidth) / 2, 0, (width + ballWidth) / 2, ballHeight);

			result.setStartPointY(ballHeight);
		}

	},

	NG(null, false, true, false) { // see SH, ZH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.fillTriangleWithForeground(0, 0, width, 0, halfWidth, halfHeight);
			result.startPointY(halfHeight);
		}

	},

	O("GOOD", true, false, false) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.drawLine(halfWidth, 0, halfWidth + halfWidth / 2, halfHeight);
			canvas.drawLine(halfWidth + halfWidth / 2, halfHeight, halfWidth, height);
		}

	},

	OO("TOO", false, true, false) { // see PET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			final int separator = height / 10;

			canvas.fillOvalWithForeground(ballWidth, 0, width - ballWidth, ballHeight);
			canvas.drawLine(width / 2, ballHeight, width / 2, ballHeight + separator);
			canvas.fillOvalWithForeground(ballWidth, ballHeight + separator, width - ballWidth,
					2 * ballHeight + separator);

			result.startPointY(2 * ballHeight + separator);
		}

	},

	OW("GO", false, true, true) { // see N, FEET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.fillOval(0, 0, width, height);
			canvas.drawOval(0, 0, width, height);
			drawCenterPointOn(canvas, width, height);
		}

	},

	OU("HOUSE", true, false, false) { // see FUN

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			U.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	OY("BOY", true, false, false) { // SEE AI, SAY

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			AY.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	P(null, false, true, true) { // see B, M

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.drawLine(halfWidth, 0, width, halfHeight);
			canvas.drawLine(width, halfHeight, halfWidth, height);
			canvas.drawLine(halfWidth, height, 0, halfHeight);
			canvas.drawLine(0, halfHeight, halfWidth, 0);
		}

	},

	R(null, false, true, true) { // see L

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			L.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	S(null, false, true, true) { // see LIT, Z, TH, W

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int startX = width / 2;
			canvas.fillTriangle(startX, 0, width, height, 0, height);
			canvas.drawTriangle(startX, 0, width, height, 0, height);
		}

	},

	SH(null, false, true, false) { // see NG, ZH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.fillTriangle(0, 0, width, 0, halfWidth, halfHeight);
			canvas.drawTriangle(0, 0, width, 0, halfWidth, halfHeight);
			result.startPointY(halfHeight);
		}

	},

	T(null, false, true, true) { // see H, D

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.fillRectangle(0, 0, width, height);
			canvas.drawRectangle(0, 0, width, height);
		}

	},

	TH(null, false, true, true) { // see LIT, Z, S, W

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			final int oneThirdWidth = width / 3;
			canvas.drawLine(oneThirdWidth, height, halfWidth, 0);
			canvas.drawLine(2 * oneThirdWidth, height, halfWidth, 0);
		}

	},

	U("FUN", true, false, false) { // see HOUSE

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, oneThirdHeight);
			canvas.drawLine(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2, oneThirdHeight);
			canvas.drawLine(halfWidth + halfWidth / 2, oneThirdHeight, halfWidth + halfWidth / 2, 2 * oneThirdHeight);
			canvas.drawLine(halfWidth + halfWidth / 2, 2 * oneThirdHeight, halfWidth, 2 * oneThirdHeight);
			canvas.drawLine(halfWidth, 2 * oneThirdHeight, halfWidth, height);
		}

	},

	V(null, false, true, true) { // see F, DH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			F.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	W(null, false, true, true) { // see LIT, Z, S, TH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			canvas.drawLine(halfWidth, height, halfWidth, 0);
		}

	},

	Y("YES", true, false, false) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, height);
			canvas.fillRectangleWithForeground(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2,
					2 * oneThirdHeight);
		}

	},

	Z(null, false, true, true) { // see LIT, S

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	ZH(null, false, true, false) { // see NG, SH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			SH.drawOn(canvas, width, height, result);

			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * height);
			final int x = (width - ballWidth) / 2;
			final int y = ballHeight * 2 / 3;
			canvas.fillOvalWithForeground(x, y, x + ballWidth, y + ballHeight);
		}

	},

	;

	private final String example;
	private final boolean lineOnly;
	private final boolean validEnd;
	private final boolean allowDrawingFromCenter;

	private PhoneticSound(String example, boolean lineOnly, boolean validEnd, boolean allowDrawingFromCenter) {
		this.example = example;
		this.lineOnly = lineOnly;
		this.validEnd = validEnd;
		this.allowDrawingFromCenter = allowDrawingFromCenter;
	}

	public NextDrawing drawOn(DrawingContext dc) {
		final NextDrawing result = dc.createNextDrawing().skipConnectionLine(this.lineOnly);
		drawOn(dc.getCanvas(), dc.getWidth(), dc.getHeight(), result);
		return result;
	}

	// we need these values everywhere

	abstract void drawOn(Canvas canvas, int width, int height, NextDrawing result);

	// used by many symbols

	protected static final void drawCenterPointOn(Canvas canvas, int width, int height) {
		final int ballWidth = (int) (SMALL_BALL_RATIO * width);
		final int ballHeight = (int) (SMALL_BALL_RATIO * height);
		final int x = (width - ballWidth) / 2;
		final int y = (height - ballHeight) / 2;
		canvas.fillOvalWithForeground(x, y, x + ballWidth, y + ballHeight);
	}

	public String getDisplayName() {
		return name();
	}

	public String getExample() {
		return this.example;
	}

	public boolean isValidEnd() {
		return this.validEnd;
	}

	public boolean isAllowDrawingFromCenter() {
		return this.allowDrawingFromCenter;
	}
}
