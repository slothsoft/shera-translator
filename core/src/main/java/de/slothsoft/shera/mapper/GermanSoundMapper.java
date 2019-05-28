package de.slothsoft.shera.mapper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

/**
 * This is the German sound mapper that translates the sounds from English to German.
 *
 * @author <a href="mailto:s.schulz@slothsoft.de">Stef Schulz</a>
 * @since 0.1.0
 */

public class GermanSoundMapper implements SoundMapper {

	private final Map<String, PhoneticSound> mapping = new HashMap<>();
	private final Map<String, PhoneticSound[]> multiMapping = new HashMap<>();

	public GermanSoundMapper() {
		addMapping("ä", PhoneticSound.A);
		addMapping("aa", PhoneticSound.AH);
		addMapping("ah", PhoneticSound.AH);
		addMapping("ö", PhoneticSound.AY);
		addMapping("ee", PhoneticSound.AY);
		addMapping("eh", PhoneticSound.AY);
		addMapping("b", PhoneticSound.B);
		addMapping("tsch", PhoneticSound.CH);
		addMapping("d", PhoneticSound.D);
//		addMapping("?", PhoneticSound.DH);
		addMapping("e", PhoneticSound.E);
		addMapping("ie", PhoneticSound.EE);
		addMapping("ih", PhoneticSound.EE);
		addMapping("ü", PhoneticSound.EE);
		addMapping("f", PhoneticSound.F);
		addMapping("g", PhoneticSound.G);
		addMapping("h", PhoneticSound.H);
		addMapping("i", PhoneticSound.I);
		addMapping("y", PhoneticSound.I);
		addMapping("ei", PhoneticSound.AI);
		addMapping("ai", PhoneticSound.AI);
		addMapping("dsch", PhoneticSound.J);
		addMapping("k", PhoneticSound.K);
		addMapping("c", PhoneticSound.K);
		addMapping("q", PhoneticSound.K);
		addMapping("l", PhoneticSound.L);
		addMapping("m", PhoneticSound.M);
		addMapping("n", PhoneticSound.N);
//		addMapping("?", PhoneticSound.NG);
		addMapping("u", PhoneticSound.O);
		addMapping("uh", PhoneticSound.OO);
		addMapping("uu", PhoneticSound.OO);
		addMapping("o", PhoneticSound.OW);
		addMapping("au", PhoneticSound.OU);
//		addMapping("?", PhoneticSound.OY);
		addMapping("p", PhoneticSound.P);
		addMapping("r", PhoneticSound.R);
		addMapping("ß", PhoneticSound.S);
		addMapping("sch", PhoneticSound.SH);
		addMapping("sh", PhoneticSound.SH); // "She-Ra"!
		addMapping("t", PhoneticSound.T);
//		addMapping("?", PhoneticSound.TH);
		addMapping("a", PhoneticSound.U);
		addMapping("w", PhoneticSound.V);
		addMapping("v", PhoneticSound.V);
//		addMapping("?", PhoneticSound.W);
		addMapping("j", PhoneticSound.Y);
		addMapping("s", PhoneticSound.Z);
//		addMapping("?", PhoneticSound.ZH);

		addMultiMapping("st", PhoneticSound.SH, PhoneticSound.T);
		addMultiMapping("sp", PhoneticSound.SH, PhoneticSound.P);
		addMultiMapping("x", PhoneticSound.K, PhoneticSound.S);
		addMultiMapping("z", PhoneticSound.T, PhoneticSound.S);
	}

	private void addMapping(String letter, PhoneticSound sound) {
		assertNewLetter(letter);
		this.mapping.put(letter, sound);
	}

	private void assertNewLetter(String letter) {
		if (this.mapping.containsKey(letter) || this.multiMapping.containsKey(letter))
			throw new IllegalArgumentException("Know letter " + letter + " already!");
	}

	private void addMultiMapping(String letter, PhoneticSound... sounds) {
		assertNewLetter(letter);
		this.multiMapping.put(letter, sounds);
	}

	@Override
	public String getDisplayName() {
		return "Deutsch";
	}

	@Override
	public Set<String> getSupportedLetters() {
		final Set<String> result = new HashSet<>();
		result.addAll(this.mapping.keySet());
		result.addAll(this.multiMapping.keySet());
		return result;
	}

	@Override
	public PhoneticSound[] getSounds(String letter) {
		PhoneticSound[] result = this.multiMapping.get(letter);
		if (result == null) {
			result = new PhoneticSound[]{this.mapping.get(letter)};
		}
		return result;
	}

	@Override
	public String toString() {
		return "GermanSoundMapper [" + this.mapping.size() + " letters]";
	}

}
