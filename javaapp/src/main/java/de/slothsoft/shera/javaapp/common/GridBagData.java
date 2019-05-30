package de.slothsoft.shera.javaapp.common;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagData extends java.awt.GridBagConstraints {

	private static final long serialVersionUID = -926583559831460452L;
	static final int DEFAULT_GAP = 3;
	static final Insets DEFAULT_INSETS = new Insets(DEFAULT_GAP, DEFAULT_GAP, DEFAULT_GAP, DEFAULT_GAP);

	public static GridBagData forPanel(int x, int y) {
		return new GridBagData(x, y, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.BOTH, DEFAULT_INSETS,
				0, 0);
	}

	public static GridBagData forLabel(int x, int y) {
		return new GridBagData(x, y, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE,
				DEFAULT_INSETS, 0, 0);
	}

	public static GridBagData forControl(int x, int y) {
		return new GridBagData(x, y, 1, 1, 1.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL,
				DEFAULT_INSETS, 0, 0);
	}

	public GridBagData() {
		super();
	}

	public GridBagData(int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor,
			int fill, Insets insets, int ipadx, int ipady) {
		super(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady);
	}

	public GridBagData fill(int newValue) {
		this.fill = newValue;
		return this;
	}

	public GridBagData gridx(int newValue) {
		this.gridx = newValue;
		return this;
	}

	public GridBagData gridy(int newValue) {
		this.gridy = newValue;
		return this;
	}

	public GridBagData gridwidth(int newValue) {
		this.gridwidth = newValue;
		return this;
	}

	public GridBagData gridheight(int newValue) {
		this.gridheight = newValue;
		return this;
	}

	public GridBagData weightx(double newValue) {
		this.weightx = newValue;
		return this;
	}

	public GridBagData weighty(double newValue) {
		this.weighty = newValue;
		return this;
	}

	public GridBagData anchor(int newValue) {
		this.anchor = newValue;
		return this;
	}

	public GridBagData insets(int newValue) {
		return insets(new Insets(newValue, newValue, newValue, newValue));
	}

	public GridBagData insets(Insets newValue) {
		this.insets = newValue;
		return this;
	}

	public GridBagData ipadx(int newValue) {
		this.ipadx = newValue;
		return this;
	}

	public GridBagData ipady(int newValue) {
		this.ipady = newValue;
		return this;
	}

}