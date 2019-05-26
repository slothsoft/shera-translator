package de.slothsoft.shera;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

public class PhoneticSoundReaderTest {

	@Test
	public void testIn42Out13() throws Exception {
		final PhoneticSoundReader reader = new PhoneticSoundReader();
		try (InputStream input = getClass().getResourceAsStream("/in42-out13.png")) {
			final PhoneticSoundReader.Result result = reader.readInputStream(input);

			Assert.assertNotNull(result);
			Assert.assertEquals(4, result.inputX);
			Assert.assertEquals(2, result.inputY);
			Assert.assertEquals(1, result.outputX);
			Assert.assertEquals(3, result.outputY);
		}
	}

	@Test
	public void testIn13NoOut() throws Exception {
		final PhoneticSoundReader reader = new PhoneticSoundReader();
		try (InputStream input = getClass().getResourceAsStream("/in13-noOut.png")) {
			reader.readInputStream(input);
			Assert.fail("There should have been an exception!");
		} catch (final IOException e) {
			Assert.assertEquals("No output (#00FF00) defined!", e.getMessage());
		}
	}

	@Test
	public void testIn13TwoOut() throws Exception {
		final PhoneticSoundReader reader = new PhoneticSoundReader();
		try (InputStream input = getClass().getResourceAsStream("/in13-twoOut.png")) {
			reader.readInputStream(input);
			Assert.fail("There should have been an exception!");
		} catch (final IOException e) {
			Assert.assertEquals("Too many outputs (#00FF00) defined: 0|0 and 9|9", e.getMessage());
		}
	}

	@Test
	public void testNoInOut42() throws Exception {
		final PhoneticSoundReader reader = new PhoneticSoundReader();
		try (InputStream input = getClass().getResourceAsStream("/noIn-out42.png")) {
			reader.readInputStream(input);
			Assert.fail("There should have been an exception!");
		} catch (final IOException e) {
			Assert.assertEquals("No input (#FF0000) defined!", e.getMessage());
		}
	}

	@Test
	public void testTwoInOut42() throws Exception {
		final PhoneticSoundReader reader = new PhoneticSoundReader();
		try (InputStream input = getClass().getResourceAsStream("/twoIn-out42.png")) {
			reader.readInputStream(input);
			Assert.fail("There should have been an exception!");
		} catch (final IOException e) {
			Assert.assertEquals("Too many inputs (#FF0000) defined: 9|0 and 0|9", e.getMessage());
		}
	}
}
