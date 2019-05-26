package de.slothsoft.shera;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PhoneticSoundTest {

	@Parameters(name = "{0}")
	public static Collection<Object[]> data() {
		final List<Object[]> result = new ArrayList<>();
		for (final PhoneticSound sound : PhoneticSound.values()) {
			result.add(new Object[]{sound});
		}
		return result;
	}

	private final PhoneticSound sound;

	public PhoneticSoundTest(PhoneticSound sound) {
		this.sound = sound;
	}

	@Test
	public void testGetInfo() throws Exception {
		Assert.assertNotNull(this.sound.getInfo());
	}
}
