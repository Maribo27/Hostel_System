package by.tc.hostel_system.service.validation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValidatorTest {

	@Test
	public void isLanguage() {
	}

	@Test
	public void trueLanguage() {
		String password = "ru";
		boolean expected = true;
		boolean actual = true;
		try {
			Validator.isLanguage(password);
		} catch (LangNotSupportedException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongLanguage() {
		String password = "it";
		boolean expected = false;
		boolean actual = true;
		try {
			Validator.isLanguage(password);
		} catch (LangNotSupportedException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void isSign() {
	}

	@Test
	public void trueSign() {
		String password = "plus";
		boolean expected = true;
		boolean actual = true;
		try {
			UserValidator.isSign(password);
		} catch (InputException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongSign() {
		String password = "check";
		boolean expected = false;
		boolean actual = true;
		try {
			UserValidator.isSign(password);
		} catch (InputException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void trueDate() {
		String password = "2015-12-10";
		boolean expected = true;
		boolean actual = true;
		try {
			Validator.isDate(password);
		} catch (NotDateException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}

	@Test
	public void wrongDate() {
		String password = "1245-544-5";
		boolean expected = false;
		boolean actual = true;
		try {
			Validator.isDate(password);
		} catch (NotDateException e) {
			actual = false;
		}
		assertEquals(expected, actual);
	}
}