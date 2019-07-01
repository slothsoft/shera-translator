package de.slothsoft.shera;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordGrouperTest {

	private PhoneticSound validEnd;
	private PhoneticSound noValidEnd;
	private PhoneticSound allowDrawingFromCenter;
	private PhoneticSound noAllowDrawingFromCenter;

	private final WordGrouper grouper = new WordGrouper();

	@Before
	public void setUp() {
		this.validEnd = findSound(PhoneticSound::isValidEnd, true);
		this.noValidEnd = findSound(PhoneticSound::isValidEnd, false);
		this.allowDrawingFromCenter = findSound(PhoneticSound::isAllowDrawingFromCenter, true);
		this.noAllowDrawingFromCenter = findSound(PhoneticSound::isAllowDrawingFromCenter, false);
	}

	private static PhoneticSound findSound(Predicate<PhoneticSound> property, boolean value) {
		for (final PhoneticSound sound : PhoneticSound.values())
			if (property.test(sound) == value) return sound;
		Assert.fail("Could not find sound with property: " + property);
		return null;
	}

	@Test
	public void testGroupWordTwoPerfectGroups() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.validEnd,
				this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(4, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTwoGroups() throws Exception {
		final PhoneticSound[] sounds = {this.validEnd, this.validEnd, this.validEnd, this.validEnd, this.validEnd,
				this.validEnd, this.validEnd, this.validEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(4, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTwoAlmostPerfectGroupsSecondSmaller() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.validEnd,
				this.noValidEnd, this.noValidEnd, this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(4, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTwoAlmostPerfectGroupsFirstSmaller() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.validEnd, this.noValidEnd,
				this.noValidEnd, this.noValidEnd, this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(3, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTwoAlmostPerfectGroupsSecondBigger() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.validEnd,
				this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(4, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTwoAlmostPerfectGroupsFirstBigger() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.validEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(1, word.getGroupSeparatorIndexes().length);
		Assert.assertEquals(5, word.getGroupSeparatorIndexes()[0]);
	}

	@Test
	public void testGroupWordTooSmall() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd};

		final Word word = this.grouper.groupWord(sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());
		Assert.assertEquals(0, word.getGroupSeparatorIndexes().length);
	}

	@Test
	public void testGetMalusForGroupPerfectGroup() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.validEnd};

		Assert.assertEquals(0, this.grouper.getMalusForGroup(sounds, 0, 4));
	}

	@Test
	public void testGetMalusForGroupPerfectGroupInTheMiddle() throws Exception {
		final PhoneticSound[] sounds = {this.allowDrawingFromCenter, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.validEnd, this.noAllowDrawingFromCenter, this.noAllowDrawingFromCenter};

		Assert.assertEquals(0, this.grouper.getMalusForGroup(sounds, 1, 5));
	}

	@Test
	public void testGetMalusForGroupNoEnd() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.noValidEnd};

		Assert.assertEquals(WordGrouper.MALUS_NO_END, this.grouper.getMalusForGroup(sounds, 0, 4));
	}

	@Test
	public void testGetMalusForGroupNoEndButLast() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd};

		Assert.assertEquals(0, this.grouper.getMalusForGroup(sounds, 0, 4));
	}

	@Test
	public void testGetMalusForGroupOneTooSmall() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.validEnd};

		Assert.assertEquals(1, this.grouper.getMalusForGroup(sounds, 0, 3));
	}

	@Test
	public void testGetMalusForGroupOneTooSmallNoEnd() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd};

		Assert.assertEquals(WordGrouper.MALUS_NO_END + 1, this.grouper.getMalusForGroup(sounds, 0, 3));
	}

	@Test
	public void testGetMalusForGroupOneTooBig() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.validEnd};

		Assert.assertEquals(1, this.grouper.getMalusForGroup(sounds, 0, 5));
	}

	@Test
	public void testGetMalusForGroupOneTooBigNoEnd() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.noValidEnd, this.noValidEnd};

		Assert.assertEquals(WordGrouper.MALUS_NO_END + 1, this.grouper.getMalusForGroup(sounds, 0, 5));
	}

	@Test
	public void testGetMalusForGroupTwoTooSmall() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.validEnd};

		Assert.assertEquals(2, this.grouper.getMalusForGroup(sounds, 0, 2));
	}

	@Test
	public void testGetMalusForGroupTooSmall() throws Exception {
		final PhoneticSound[] sounds = {this.validEnd};

		Assert.assertEquals(WordGrouper.MALUS_TOO_SMALL, this.grouper.getMalusForGroup(sounds, 0, 1));
	}

	@Test
	public void testGetMalusForGroupTooSmallNoEnd() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd};

		Assert.assertEquals(WordGrouper.MALUS_TOO_SMALL + WordGrouper.MALUS_NO_END,
				this.grouper.getMalusForGroup(sounds, 0, 1));
	}

	@Test
	public void testGetMalusForGroupTooBig() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.noValidEnd, this.validEnd};

		Assert.assertEquals(WordGrouper.MALUS_TOO_BIG, this.grouper.getMalusForGroup(sounds, 0, 6));
	}

	@Test
	public void testGetMalusForGroupTooBigNoEnd() throws Exception {
		final PhoneticSound[] sounds = {this.noValidEnd, this.noValidEnd, this.noValidEnd, this.noValidEnd,
				this.noValidEnd, this.noValidEnd, this.noValidEnd};

		Assert.assertEquals(WordGrouper.MALUS_TOO_BIG + WordGrouper.MALUS_NO_END,
				this.grouper.getMalusForGroup(sounds, 0, 6));
	}

	@Test
	public void testGetPreferredIndexDefault() throws Exception {
		final String error = "Wrong indexes: " + Arrays.toString(this.grouper.preferredIndexMap);
		Assert.assertEquals(error, 4, this.grouper.getPreferredIndex(0));
		Assert.assertEquals(error, 5, this.grouper.getPreferredIndex(1));
		Assert.assertEquals(error, 3, this.grouper.getPreferredIndex(2));
		Assert.assertEquals(error, 2, this.grouper.getPreferredIndex(3));
	}

	@Test
	public void testGetPreferredIndexMaxGroupSizeLast() throws Exception {
		this.grouper.preferredGroupSize(3).minGroupSize(1).maxGroupSize(7);

		final String error = "Wrong indexes: " + Arrays.toString(this.grouper.preferredIndexMap);
		Assert.assertEquals(error, 3, this.grouper.getPreferredIndex(0));
		Assert.assertEquals(error, 4, this.grouper.getPreferredIndex(1));
		Assert.assertEquals(error, 2, this.grouper.getPreferredIndex(2));
		Assert.assertEquals(error, 5, this.grouper.getPreferredIndex(3));
		Assert.assertEquals(error, 1, this.grouper.getPreferredIndex(4));
		Assert.assertEquals(error, 6, this.grouper.getPreferredIndex(5));
		Assert.assertEquals(error, 7, this.grouper.getPreferredIndex(6));
	}

	@Test
	public void testGetPreferredIndexMinGroupSizeLast() throws Exception {
		this.grouper.preferredGroupSize(3).maxGroupSize(7).minGroupSize(1);

		final String error = "Wrong indexes: " + Arrays.toString(this.grouper.preferredIndexMap);
		Assert.assertEquals(error, 3, this.grouper.getPreferredIndex(0));
		Assert.assertEquals(error, 4, this.grouper.getPreferredIndex(1));
		Assert.assertEquals(error, 2, this.grouper.getPreferredIndex(2));
		Assert.assertEquals(error, 5, this.grouper.getPreferredIndex(3));
		Assert.assertEquals(error, 1, this.grouper.getPreferredIndex(4));
		Assert.assertEquals(error, 6, this.grouper.getPreferredIndex(5));
		Assert.assertEquals(error, 7, this.grouper.getPreferredIndex(6));
	}

	@Test
	public void testGetPreferredIndexPreferredGroupSizeLast() throws Exception {
		this.grouper.maxGroupSize(7).minGroupSize(1).preferredGroupSize(3);

		final String error = "Wrong indexes: " + Arrays.toString(this.grouper.preferredIndexMap);
		Assert.assertEquals(error, 3, this.grouper.getPreferredIndex(0));
		Assert.assertEquals(error, 4, this.grouper.getPreferredIndex(1));
		Assert.assertEquals(error, 2, this.grouper.getPreferredIndex(2));
		Assert.assertEquals(error, 5, this.grouper.getPreferredIndex(3));
		Assert.assertEquals(error, 1, this.grouper.getPreferredIndex(4));
		Assert.assertEquals(error, 6, this.grouper.getPreferredIndex(5));
		Assert.assertEquals(error, 7, this.grouper.getPreferredIndex(6));
	}
}
