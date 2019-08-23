package de.slothsoft.shera;

import org.junit.Assert;
import org.junit.Test;

public class WordTest {

	@Test
	public void testGenerateGroupNoIndexes() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);

		Assert.assertEquals(1, word.getGroupCount());
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH},
				word.generateGroup(0));
	}

	@Test
	public void testGenerateGroupNoIndexesGroupTooBig() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);

		try {
			word.generateGroup(1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("There is only 1 group, so index 1 was not found!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupNoIndexesGroupTooSmall() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);

		try {
			word.generateGroup(-1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("Group index must be postive!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupEmptyIndex() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[0]);

		Assert.assertEquals(1, word.getGroupCount());
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH},
				word.generateGroup(0));
	}

	@Test
	public void testGenerateGroupEmptyIndexGroupTooBig() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[0]);

		try {
			word.generateGroup(1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("There is only 1 group, so index 1 was not found!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupEmptyIndexGroupTooSmall() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[0]);

		try {
			word.generateGroup(-1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("Group index must be postive!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupWithIndex() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1});

		Assert.assertEquals(2, word.getGroupCount());
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.A}, word.generateGroup(0));
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.B, PhoneticSound.CH}, word.generateGroup(1));
	}

	@Test
	public void testGenerateGroupWithIndexGroupTooBig() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1});

		try {
			word.generateGroup(2);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("There are only 2 groups, so index 2 was not found!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupWithIndexGroupTooSmall() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1});

		try {
			word.generateGroup(-1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("Group index must be postive!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupWithIndexes() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1, 2});

		Assert.assertEquals(3, word.getGroupCount());
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.A}, word.generateGroup(0));
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.B}, word.generateGroup(1));
		Assert.assertArrayEquals(new PhoneticSound[]{PhoneticSound.CH}, word.generateGroup(2));
	}

	@Test
	public void testGenerateGroupWithIndexesGroupTooBig() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1, 2});

		try {
			word.generateGroup(3);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("There are only 3 groups, so index 3 was not found!", e.getMessage());
		}
	}

	@Test
	public void testGenerateGroupWithIndexesGroupTooSmall() throws Exception {
		final Word word = new Word(PhoneticSound.A, PhoneticSound.B, PhoneticSound.CH);
		word.setGroupSeparatorIndexes(new int[]{1, 2});

		try {
			word.generateGroup(-1);
			Assert.fail("There should have been an exception!");
		} catch (final IllegalArgumentException e) {
			Assert.assertEquals("Group index must be postive!", e.getMessage());
		}
	}
}
