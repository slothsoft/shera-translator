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

	// TODO: I calculated the point size as "partWidth" / "partHeight" alot
	// maybe that's better of as an offset on #drawCenterPointOn

	A("SAD", true) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int y = height / 2;
			final int endPoint = width / 2 - ballWidth;

			canvas.drawLine(width / 2, 0, width / 2, y);
			canvas.drawLine(width / 2, y, width / 2 - ballWidth, y);
			canvas.drawLine(width / 2 - ballWidth, y, endPoint, height);

			result.setStartPointX(endPoint - width / 2);
			result.setSkipConnectionLine(true);
		}

	},

	AH("ALL", true) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int partWidth = width / 5;
			final int partHeight = height / 5;
			final int y = (height - partHeight) / 2;
			final int endPoint = width / 2 - partWidth;

			canvas.drawLine(width / 2, 0, width / 2, y);
			canvas.drawLine(width / 2, y, width / 2 - partWidth, y + partHeight);
			canvas.drawLine(width / 2 - partWidth, y + partHeight, endPoint, height);

			result.setStartPointX(endPoint - width / 2);
			result.setSkipConnectionLine(true);
		}

	},

	AY("SAY", true) { // SEE AI, BOY

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

	B { // see M, P

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			P.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	CH { // see J

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			canvas.drawTriangle(halfWidth, 0, halfWidth, height, -halfWidth / 2, height);
			result.setStartPointX(-3 * halfWidth / 4); // looks centerish
		}

	},

	D { // see H, T

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.drawRectangle(0, 0, width, height);
			drawCenterPointOn(canvas, width, height);
		}

	},

	DH { // see F, V

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			F.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			final int oneThirdWidth = width / 3;
			canvas.drawLine(oneThirdWidth, 0, halfWidth, height);
			canvas.drawLine(2 * oneThirdWidth, 0, halfWidth, height);
		}

	},

	E("PET", false) { // see TOO

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			final int separator = height / 10;

			canvas.drawOval(ballWidth, 0, width - ballWidth, ballHeight);
			canvas.drawLine(width / 2, ballHeight, width / 2, ballHeight + separator);
			canvas.drawOval(ballWidth, ballHeight + separator, width - ballWidth, 2 * ballHeight + separator);

			result.startPointY(2 * ballHeight + separator);
		}

	},

	EE("FEET", false) { // see N, GO

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			canvas.drawOval((width - ballWidth) / 2, 0, (width + ballWidth) / 2, ballHeight);

			result.setStartPointY(ballHeight);
		}

	},

	F { // see DH, V

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			canvas.drawLine(0, 0, width, 0);
			canvas.drawLine(width, 0, halfWidth, height);
			canvas.drawLine(halfWidth, height, 0, 0);
		}

	},

	G { // see K

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			K.drawOn(canvas, width, height, result);

			final int partWidth = width / 5;
			final int partHeight = height / 5;
			final int x = (width - partWidth) / 2;
			final int y = 2 * height / 3 - partHeight / 2;
			canvas.fillOval(x, y, x + partWidth, y + partHeight);
		}

	},

	H { // see T, D

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.fillRectangle(0, 0, width, height);
		}

	},

	I("LIT", false) { // see S

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int startX = width / 2;
			canvas.fillTriangle(startX, 0, width, height, 0, height);
		}

	},

	AI("I", true) { // see SAY

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, height);
			canvas.fillTriangle(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2, halfHeight, halfWidth,
					2 * oneThirdHeight);
		}

	},

	J { // see CH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			CH.drawOn(canvas, width, height, result);

			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * width);
			final int x = width / 8;// looks centerish
			final int y = height - 2 * ballHeight;
			canvas.fillOval(x, y, x + ballWidth, y + ballHeight);
		}

	},

	K { // see G

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

	L { // see R

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int quarterWidth = width / 4;
			canvas.drawLine(quarterWidth, 0, width, 0);
			canvas.drawLine(width, 0, width - quarterWidth, height);
			canvas.drawLine(width - quarterWidth, height, 0, height);
			canvas.drawLine(0, height, quarterWidth, 0);
		}

	},

	M { // see B, P

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.fillTriangle(halfWidth, 0, width, halfHeight, 0, halfWidth);
			canvas.fillTriangle(halfWidth, height, 0, halfWidth, width, halfHeight);
		}

	},

	N { // see FEET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = (int) (SMALL_BALL_RATIO * width);
			final int ballHeight = (int) (SMALL_BALL_RATIO * height);
			canvas.fillOval((width - ballWidth) / 2, 0, (width + ballWidth) / 2, ballHeight);

			result.setStartPointY(ballHeight);
		}

	},

	NG { // see SH, ZH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.fillTriangle(0, 0, width, 0, halfWidth, halfHeight);
			result.startPointY(halfHeight);
		}

	},

	O("GOOD", true) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.drawLine(halfWidth, 0, halfWidth + halfWidth / 2, halfHeight);
			canvas.drawLine(halfWidth + halfWidth / 2, halfHeight, halfWidth, height);
		}

	},

	OO("TOO", false) { // see PET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int ballWidth = width / 3;
			final int ballHeight = height / 3;
			final int separator = height / 10;

			canvas.fillOval(ballWidth, 0, width - ballWidth, ballHeight);
			canvas.drawLine(width / 2, ballHeight, width / 2, ballHeight + separator);
			canvas.fillOval(ballWidth, ballHeight + separator, width - ballWidth, 2 * ballHeight + separator);

			result.startPointY(2 * ballHeight + separator);
		}

	},

	OW("GO", false) { // see N, FEET

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.drawOval(0, 0, width, height);
			drawCenterPointOn(canvas, width, height);
		}

	},

	OU("HOUSE", true) { // see FUN

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			U.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	OY("BOY", true) { // SEE AI, SAY

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			AY.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	P { // see B, M

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

	R { // see L

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			L.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	S { // see LIT, Z, TH, W

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int startX = width / 2;
			canvas.drawTriangle(startX, 0, width, height, 0, height);
		}

	},

	SH { // see NG, ZH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int halfHeight = height / 2;
			canvas.drawTriangle(0, 0, width, 0, halfWidth, halfHeight);
			result.startPointY(halfHeight);
		}

	},

	T { // see H, D

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			canvas.drawRectangle(0, 0, width, height);
		}

	},

	TH { // see LIT, Z, S, W

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			final int oneThirdWidth = width / 3;
			canvas.drawLine(oneThirdWidth, height, halfWidth, 0);
			canvas.drawLine(2 * oneThirdWidth, height, halfWidth, 0);
		}

	},

	U("FUN", true) { // see HOUSE

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

	V { // see F, DH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			F.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	W { // see LIT, Z, S, TH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			final int halfWidth = width / 2;
			canvas.drawLine(halfWidth, height, halfWidth, 0);
		}

	},

	Y("YES", true) {

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			final int halfWidth = width / 2;
			final int oneThirdHeight = height / 3;
			canvas.drawLine(halfWidth, 0, halfWidth, height);
			canvas.fillRectangle(halfWidth, oneThirdHeight, halfWidth + halfWidth / 2, 2 * oneThirdHeight);
		}

	},

	Z { // see LIT, S

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			S.drawOn(canvas, width, height, result);
			drawCenterPointOn(canvas, width, height);
		}

	},

	ZH { // see NG, SH

		@Override
		void drawOn(Canvas canvas, int width, int height, NextDrawing result) {
			SH.drawOn(canvas, width, height, result);

			final int partWidth = width / 5;
			final int partHeight = height / 5;
			final int x = (width - partWidth) / 2;
			final int y = partHeight;
			canvas.fillOval(x, y, x + partWidth, y + partHeight);
		}

	},

	;

	private String example;
	private boolean lineOnly;

	private PhoneticSound() {
		this(null, false);
	}

	private PhoneticSound(String example, boolean lineOnly) {
		this.example = example;
		this.lineOnly = lineOnly;
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
		canvas.fillOval(x, y, x + ballWidth, y + ballHeight);
	}

	public String getDisplayName() {
		return name();
	}

	public String getExample() {
		return this.example;
	}
}
