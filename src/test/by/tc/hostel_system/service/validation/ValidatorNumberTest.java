package by.tc.hostel_system.service.validation;

import by.tc.hostel_system.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorNumberTest {

	@Test
	public void truePositiveNumber() {
		boolean expected = true;
		final int positiveNumber = 56;
		boolean actual = true;
		try {
			UserValidator.checkPositiveNumber(positiveNumber);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongPositiveNumber() {
		boolean expected = false;
		final int positiveNumber = -54;
		boolean actual = true;
		try {
			UserValidator.checkPositiveNumber(positiveNumber);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void trueId() {
		boolean expected = true;
		final int id = 256;
		boolean actual = true;
		try {
			UserValidator.checkNumber(id);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongId() {
		boolean expected = false;
		final int id = -9;
		boolean actual = true;
		try {
			UserValidator.checkNumber(id);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void trueNumber() {
		boolean expected = true;
		final String number = "560";
		boolean actual = true;
		try {
			Validator.isNumber(number);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongNumber() {
		boolean expected = false;
		final String number = "hg54kj";
		boolean actual = true;
		try {
			Validator.isNumber(number);
		} catch (NotNumberException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}
}