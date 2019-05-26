package de.slothsoft.shera;

import java.io.IOException;
import java.io.InputStream;

/**
 * All the phonetic sounds the first ones writing has. See <a href=
 * "http://www.dreamworkstv.com/wp-content/uploads/2015/07/SheRa_FirstOnes_Language.pdf">Dreamworks
 * handy guide</a>.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public enum PhoneticSound {
	A("SAD", true),

	AH("ALL", true),

	AY("SAY", true),

	B,

	CH,

	D,

	DH,

	E("PET", false),

	EE("FEET", false),

	F,

	G,

	H,

	I("LIT", false),

	AI("I", true),

	J,

	K,

	L,

	M,

	N,

	NG,

	O("GOOD", true),

	OO("TOO", false),

	OW("GO", false),

	OU("HOUSE", true),

	OY("BOY", true),

	P,

	R,

	S,

	SH,

	T,

	TH,

	U("FUN", true),

	V,

	W,

	Y("YES", true),

	Z,

	ZH,;

	private boolean lineOnly;

	private PhoneticSoundReader.Result info;

	private PhoneticSound() {
		this(null, false);
	}

	private PhoneticSound(String example, boolean lineOnly) {
		this.lineOnly = lineOnly;
	}

	PhoneticSoundReader.Result getInfo() {
		if (this.info == null) {
			try (InputStream input = getClass().getResourceAsStream("/" + name() + ".png")) {
				this.info = new PhoneticSoundReader().readInputStream(input);
			} catch (final IOException e) {
				throw new IllegalArgumentException("Could not read image for " + name(), e);
			}
		}
		return this.info;
	}
}
