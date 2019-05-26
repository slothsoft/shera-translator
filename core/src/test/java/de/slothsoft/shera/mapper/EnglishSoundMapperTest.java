package de.slothsoft.shera.mapper;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.PhoneticSound;
import de.slothsoft.shera.SoundMapper;

public class EnglishSoundMapperTest {

	private final SoundMapper mapper = new EnglishSoundMapper();

	@Test
	public void testGetSupportedLetters() throws Exception {
		final Set<String> letters = this.mapper.getSupportedLetters();

		Assert.assertTrue("Wrong set: " + letters, letters.contains("b"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("h"));
		Assert.assertTrue("Wrong set: " + letters, letters.contains("t"));
	}

	@Test
	public void testGetSupportedLettersNotSupported() throws Exception {
		final Set<String> letters = this.mapper.getSupportedLetters();

		Assert.assertFalse("Wrong set: " + letters, letters.contains("sch"));
		Assert.assertFalse("Wrong set: " + letters, letters.contains("ß"));
	}

	@Test
	public void testGetSound() throws Exception {
		Assert.assertEquals(PhoneticSound.B, this.mapper.getSounds("b")[0]);
		Assert.assertEquals(PhoneticSound.H, this.mapper.getSounds("h")[0]);
		Assert.assertEquals(PhoneticSound.T, this.mapper.getSounds("t")[0]);
	}
}
