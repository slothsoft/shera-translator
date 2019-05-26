package de.slothsoft.shera.mapper;

import java.util.Collections;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.PhoneticSound;

public class DefaultSoundMapperTest {

	private DefaultSoundMapper mapper;

	@Test
	public void testGetSupportedLetters() throws Exception {
		this.mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		final Set<String> letters = this.mapper.getSupportedLetters();

		Assert.assertTrue("Wrong set: " + letters, letters.contains("a"));
		Assert.assertFalse("Wrong set: " + letters, letters.contains("b"));
	}

	@Test
	public void testGetSound() throws Exception {
		this.mapper = new DefaultSoundMapper(Collections.singletonMap("p", PhoneticSound.P));

		Assert.assertEquals(PhoneticSound.P, this.mapper.getSounds("p")[0]);
	}

	@Test
	public void testAddMapping() throws Exception {
		this.mapper = new DefaultSoundMapper(Collections.singletonMap("p", PhoneticSound.P));

		Assert.assertFalse("Wrong set: " + this.mapper.getSupportedLetters(),
				this.mapper.getSupportedLetters().contains("d"));
		this.mapper.addMapping("d", PhoneticSound.D);
		Assert.assertTrue("Wrong set: " + this.mapper.getSupportedLetters(),
				this.mapper.getSupportedLetters().contains("d"));
	}

	@Test
	public void testRemoveMapping() throws Exception {
		this.mapper = new DefaultSoundMapper(Collections.singletonMap("t", PhoneticSound.T));

		Assert.assertTrue("Wrong set: " + this.mapper.getSupportedLetters(),
				this.mapper.getSupportedLetters().contains("t"));
		this.mapper.removeMapping("t");
		Assert.assertFalse("Wrong set: " + this.mapper.getSupportedLetters(),
				this.mapper.getSupportedLetters().contains("t"));
	}
}
