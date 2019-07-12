package de.slothsoft.shera;

/**
 * A line that runs over the drawing surface. The formular is <code>y = mx + b</code>,
 * where m is the slope and b is the intercept.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.4.0
 */

public class Line {

	public static Line through(double x1, double y1, double x2, double y2) {
		final double slope = (y1 - y2) / (x1 - x2);
		final double intercept = y1 - slope * x1;
		return new Line().slope(slope).intercept(intercept);
	}

	double slope;
	double intercept;

	public double calculateY(double x) {
		return this.slope * x + this.intercept;
	}

	public double getIntercept() {
		return this.intercept;
	}

	public Line intercept(double newIntercept) {
		setIntercept(newIntercept);
		return this;
	}

	public void setIntercept(double intercept) {
		this.intercept = intercept;
	}

	public double getSlope() {
		return this.slope;
	}

	public Line slope(double newSlope) {
		setSlope(newSlope);
		return this;
	}

	public void setSlope(double slope) {
		this.slope = slope;
	}

	@Override
	public String toString() {
		return "Line [y = " + this.slope + "y "
				+ (this.intercept < 0 ? ("- " + Math.abs(this.intercept)) : ("+ " + this.intercept)) + "]";
	}

}
