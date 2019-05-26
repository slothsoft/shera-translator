package de.slothsoft.shera.mapper;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

public class GermanSoundMapperTest {

	private final static String SPECIAL_LETTERS = "äöüß";
	private final static PhoneticSound[] SPECIAL_LETTERS_SOUNDS = {PhoneticSound.A, PhoneticSound.AY, PhoneticSound.EE,
			PhoneticSound.S};

	private final SoundMapper mapper = new GermanSoundMapper();

	@Test
	public void testGetSupportedLetters() throws Exception {
		final Set<String> letters = this.mapper.getSupportedLetters();

		Assert.assertTrue("Wrong set: " + letters, letters.contains("b"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("h"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("t"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("sch"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("st"));
	}

	@Test
	public void testGetSupportedLettersNotSupported() throws Exception {
		final Set<String> letters = this.mapper.getSupportedLetters();

		Assert.assertFalse("Wrong set: " + letters, letters.contains("�"));
	}

	@Test
	public void testGetSound() throws Exception {
		Assert.assertEquals(PhoneticSound.B, this.mapper.getSounds("b")[0]);
		Assert.assertEquals(PhoneticSound.H, this.mapper.getSounds("h")[0]);
		Assert.assertEquals(PhoneticSound.SH, this.mapper.getSounds("sch")[0]);
		Assert.assertEquals(PhoneticSound.SH, this.mapper.getSounds("st")[0]);
		Assert.assertEquals(PhoneticSound.T, this.mapper.getSounds("st")[1]);
	}

	@Test
	public void testGetSupportedLettersSpecialLetters() throws Exception {
		final Set<String> letters = this.mapper.getSupportedLetters();

		for (final char c : SPECIAL_LETTERS.toCharArray()) {
			Assert.assertTrue("Letter " + c + " missing: " + letters, letters.contains(String.valueOf(c)));
		}
	}

	@Test
	public void testGetSoundsSpecialLetters() throws Exception {
		for (int i = 0; i < SPECIAL_LETTERS_SOUNDS.length; i++) {
			Assert.assertEquals(SPECIAL_LETTERS_SOUNDS[i],
					this.mapper.getSounds(String.valueOf(SPECIAL_LETTERS.charAt(i)))[0]);
		}
	}

}
