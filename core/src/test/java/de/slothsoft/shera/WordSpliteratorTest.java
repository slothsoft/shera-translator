package de.slothsoft.shera;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.mapper.DefaultSoundMapper;
import de.slothsoft.shera.mapper.EnglishSoundMapper;

public class WordSpliteratorTest {

	@Test
	public void testSplitSingleLetter() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("q", PhoneticSound.M));
		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.split("q");
		final PhoneticSound[] expectedSounds = {PhoneticSound.M};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitWord() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("c", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.split("abc");
		final PhoneticSound[] expectedSounds = {PhoneticSound.B, PhoneticSound.D, PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitSyllable() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("ab", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.split("ab");
		final PhoneticSound[] expectedSounds = {PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitIgnoreStuff() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("ab", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.split("1abc-");
		final PhoneticSound[] expectedSounds = {PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testGroupShadow() throws Exception {
		final WordSpliterator spliterator = new WordSpliterator(new EnglishSoundMapper());

		final SoundGrouping grouping = spliterator.group(WordSpliteratorExamplesTest.SOUNDS_SHADOW);

		// there is only one way to group this to have not the A at the end but at most 3
		// sounds in a group

// XXX:		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}
}
