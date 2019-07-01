package de.slothsoft.shera;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class WordGrouperExampleTest {

	@Parameters(name = "{1} ({0})")
	public static Collection<Object[]> data() {
		final List<Object[]> result = new ArrayList<>();

		result.add(data("--"));
		result.add(data("-E"));
		result.add(data("E-"));
		result.add(data("EE"));

		result.add(data("---"));
		result.add(data("E--"));
		result.add(data("-E-"));
		result.add(data("--E"));
		result.add(data("-EE"));
		result.add(data("E-E"));
		result.add(data("EE-"));
		result.add(data("EEE"));

		result.add(data("----"));
		result.add(data("-E-E"));
		result.add(data("E-E-"));
		result.add(data("EEEE"));

		result.add(data("--E---", 3));
		result.add(data("------", 4));

		result.add(data("--------", 4));
		result.add(data("E-------", 4));
		result.add(data("-E------", 4));
		result.add(data("--E-----", 3));
		result.add(data("---E----", 4));
		result.add(data("----E---", 5));
		result.add(data("-----E--", 4));
		result.add(data("------E-", 4));
		result.add(data("------E-", 4));
		result.add(data("-------E", 4));
		result.add(data("---E---E", 4));
		result.add(data("---EEEEE", 4));
		result.add(data("EEEE----", 4));
		result.add(data("-E-E-E-E", 4));
		result.add(data("EEEEEEEE", 4));

		result.add(data(new WordGrouper().maxGroupSize(6), "-----E--", 6));
		result.add(data(new WordGrouper().maxGroupSize(7), "------E-", 4));
		result.add(data(new WordGrouper().maxGroupSize(7).preferredGroupSize(7).minGroupSize(1), "------E-", 7));

		result.add(data("--E--E---", 3, 6));
		result.add(data("EEEEEEEEE", 4));

		result.add(data("--E--E-----", 3, 6));

		result.add(data("---E---E----", 4, 8));
		result.add(data("---E---E---E", 4, 8));
		result.add(data("EEEEEEEEEEEE", 4, 8));
		result.add(data("--E--E--E---", 3, 6, 9));

		return result;
	}

	private static Object[] data(String input, int... groupSeparatorIndexes) {
		return data(new WordGrouper(), input, groupSeparatorIndexes);
	}
	private static Object[] data(WordGrouper grouper, String input, int... groupSeparatorIndexes) {
		return new Object[]{grouper, input, groupSeparatorIndexes};
	}

	private final WordGrouper grouper;
	private final PhoneticSound[] sounds;
	private final int[] groupSeparatorIndexes;

	public WordGrouperExampleTest(WordGrouper grouper, String input, int[] groupSeparatorIndexes) {
		this.grouper = grouper;
		this.groupSeparatorIndexes = groupSeparatorIndexes;

		final Map<String, PhoneticSound> map = new HashMap<>();
		map.put("E", WordGrouperTest.findSound(PhoneticSound::isValidEnd, true));
		map.put("-", WordGrouperTest.findSound(PhoneticSound::isValidEnd, false));

		this.sounds = new PhoneticSound[input.length()];
		for (int i = 0; i < this.sounds.length; i++) {
			this.sounds[i] = map.get(String.valueOf(input.charAt(i)));
		}
	}

	@Test
	public void test() throws Exception {
		final Word word = this.grouper.groupWord(this.sounds);
		Assert.assertNotNull(word);
		Assert.assertNotNull(word.getGroupSeparatorIndexes());

		final String more = " (expected: " + Arrays.toString(this.groupSeparatorIndexes) + ", actual: "
				+ Arrays.toString(word.getGroupSeparatorIndexes()) + ")";
		Assert.assertEquals("Length was wrong!" + more, this.groupSeparatorIndexes.length,
				word.getGroupSeparatorIndexes().length);

		for (int i = 0; i < this.groupSeparatorIndexes.length; i++) {
			Assert.assertEquals("Index " + i + " is wrong!" + more, this.groupSeparatorIndexes[i],
					word.getGroupSeparatorIndexes()[i]);
		}
	}
}
