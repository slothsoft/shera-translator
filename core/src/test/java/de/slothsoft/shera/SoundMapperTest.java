package de.slothsoft.shera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.slothsoft.shera.mapper.EnglishSoundMapper;
import de.slothsoft.shera.mapper.GermanSoundMapper;

@RunWith(Parameterized.class)
public class SoundMapperTest {

	private final static String ABC = "abcdefghijklmnopqrstuvwxyz";

	@Parameters(name = "{0}")
	public static Collection<Object[]> data() {
		final List<Object[]> result = new ArrayList<>();
		result.add(new Object[]{new EnglishSoundMapper()});
		result.add(new Object[]{new GermanSoundMapper()});
		return result;
	}

	private final SoundMapper mapper;

	public SoundMapperTest(SoundMapper mapper) {
		this.mapper = mapper;
	}

	@Test
	public void testSplitLetters() throws Exception {
		final WordSpliterator spliterator = new WordSpliterator(this.mapper);

		for (final char c : ABC.toCharArray()) {
			final PhoneticSound[] sounds = spliterator.split(String.valueOf(c));

			Assert.assertTrue(String.valueOf(c) + " is missing!", sounds.length > 0);
		}
	}

	@Test
	public void testSplitLettersUpperCase() throws Exception {
		final WordSpliterator spliterator = new WordSpliterator(this.mapper);

		for (final char c : ABC.toCharArray()) {
			final PhoneticSound[] sounds = spliterator.split(String.valueOf(c).toUpperCase());

			Assert.assertTrue(String.valueOf(c) + " is missing!", sounds.length > 0);
		}
	}
}
