package de.slothsoft.shera.mapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

/**
 * Just so I don't have to type all these letters again...
 */

public class TemplateSoundMapper implements SoundMapper {

	private final Map<String, PhoneticSound> mapping = new HashMap<>();

	public TemplateSoundMapper() {
		addMapping("?", PhoneticSound.A);
		addMapping("?", PhoneticSound.AH);
		addMapping("?", PhoneticSound.AY);
		addMapping("?", PhoneticSound.B);
		addMapping("?", PhoneticSound.CH);
		addMapping("?", PhoneticSound.D);
		addMapping("?", PhoneticSound.DH);
		addMapping("?", PhoneticSound.E);
		addMapping("?", PhoneticSound.EE);
		addMapping("?", PhoneticSound.F);
		addMapping("?", PhoneticSound.G);
		addMapping("?", PhoneticSound.H);
		addMapping("?", PhoneticSound.I);
		addMapping("?", PhoneticSound.AI);
		addMapping("?", PhoneticSound.J);
		addMapping("?", PhoneticSound.K);
		addMapping("?", PhoneticSound.L);
		addMapping("?", PhoneticSound.M);
		addMapping("?", PhoneticSound.N);
		addMapping("?", PhoneticSound.NG);
		addMapping("?", PhoneticSound.O);
		addMapping("?", PhoneticSound.OO);
		addMapping("?", PhoneticSound.OW);
		addMapping("?", PhoneticSound.OU);
		addMapping("?", PhoneticSound.OY);
		addMapping("?", PhoneticSound.P);
		addMapping("?", PhoneticSound.R);
		addMapping("?", PhoneticSound.S);
		addMapping("?", PhoneticSound.SH);
		addMapping("?", PhoneticSound.T);
		addMapping("?", PhoneticSound.TH);
		addMapping("?", PhoneticSound.U);
		addMapping("?", PhoneticSound.V);
		addMapping("?", PhoneticSound.W);
		addMapping("?", PhoneticSound.Y);
		addMapping("?", PhoneticSound.Z);
		addMapping("?", PhoneticSound.ZH);
	}

	private void addMapping(String letter, PhoneticSound sound) {
		if (this.mapping.containsKey(letter) && !"?".equals(letter))
			throw new IllegalArgumentException("Know letter " + letter + " already!");
		addMapping(letter, sound);
	}

	@Override
	public String getDisplayName() {
		return "Template";
	}

	@Override
	public Set<String> getSupportedLetters() {
		return this.mapping.keySet();
	}

	@Override
	public PhoneticSound[] getSounds(String letter) {
		return new PhoneticSound[]{this.mapping.get(letter)};
	}

	@Override
	public String toString() {
		return "TemplateSoundMapper [" + this.mapping.size() + " letters]";
	}

}
