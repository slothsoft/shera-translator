package de.slothsoft.shera;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.shera.mapper.DefaultSoundMapper;

public class WordSpliteratorTest {

	@Test
	public void testSplitIntoSoundsSingleLetter() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("q", PhoneticSound.M));
		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.splitIntoSounds("q");
		final PhoneticSound[] expectedSounds = {PhoneticSound.M};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitIntoSoundsWord() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("c", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.splitIntoSounds("abc");
		final PhoneticSound[] expectedSounds = {PhoneticSound.B, PhoneticSound.D, PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitIntoSoundsSyllable() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("ab", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.splitIntoSounds("ab");
		final PhoneticSound[] expectedSounds = {PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitIntoSoundsIgnoreStuff() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("a", PhoneticSound.B));
		mapper.addMapping("b", PhoneticSound.D);
		mapper.addMapping("ab", PhoneticSound.H);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final PhoneticSound[] actualSounds = spliterator.splitIntoSounds("1abc-");
		final PhoneticSound[] expectedSounds = {PhoneticSound.H};

		Assert.assertArrayEquals(expectedSounds, actualSounds);
	}

	@Test
	public void testSplitIntoWordsSpace() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("e", PhoneticSound.EE));
		mapper.addMapping("o", PhoneticSound.OO);
		mapper.addMapping("a", PhoneticSound.AH);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final Word[] actualWords = spliterator.splitIntoWords("ae eo");
		final PhoneticSound[] word1 = {PhoneticSound.AH, PhoneticSound.EE};
		final PhoneticSound[] word2 = {PhoneticSound.EE, PhoneticSound.OO};

		Assert.assertEquals(2, actualWords.length);
		Assert.assertArrayEquals(word1, actualWords[0].getContent());
		Assert.assertArrayEquals(word2, actualWords[1].getContent());
	}

	@Test
	public void testSplitIntoWordsTab() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("e", PhoneticSound.EE));
		mapper.addMapping("o", PhoneticSound.OO);
		mapper.addMapping("a", PhoneticSound.AH);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final Word[] actualWords = spliterator.splitIntoWords("ae\teo");
		final PhoneticSound[] word1 = {PhoneticSound.AH, PhoneticSound.EE};
		final PhoneticSound[] word2 = {PhoneticSound.EE, PhoneticSound.OO};

		Assert.assertEquals(2, actualWords.length);
		Assert.assertArrayEquals(word1, actualWords[0].getContent());
		Assert.assertArrayEquals(word2, actualWords[1].getContent());
	}

	@Test
	public void testSplitIntoWordsEnter() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("e", PhoneticSound.EE));
		mapper.addMapping("o", PhoneticSound.OO);
		mapper.addMapping("a", PhoneticSound.AH);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final Word[] actualWords = spliterator.splitIntoWords("ae\r\neo");
		final PhoneticSound[] word1 = {PhoneticSound.AH, PhoneticSound.EE};
		final PhoneticSound[] word2 = {PhoneticSound.EE, PhoneticSound.OO};

		Assert.assertEquals(2, actualWords.length);
		Assert.assertArrayEquals(word1, actualWords[0].getContent());
		Assert.assertArrayEquals(word2, actualWords[1].getContent());
	}

	@Test
	public void testSplitIntoWordsMulti() throws Exception {
		final DefaultSoundMapper mapper = new DefaultSoundMapper(Collections.singletonMap("e", PhoneticSound.EE));
		mapper.addMapping("o", PhoneticSound.OO);
		mapper.addMapping("a", PhoneticSound.AH);

		final WordSpliterator spliterator = new WordSpliterator(mapper);

		final Word[] actualWords = spliterator.splitIntoWords("ae \t\r\neo");
		final PhoneticSound[] word1 = {PhoneticSound.AH, PhoneticSound.EE};
		final PhoneticSound[] word2 = {PhoneticSound.EE, PhoneticSound.OO};

		Assert.assertEquals(2, actualWords.length);
		Assert.assertArrayEquals(word1, actualWords[0].getContent());
		Assert.assertArrayEquals(word2, actualWords[1].getContent());
	}
}
