package by.tc.hostel_system.util;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.assertEquals;

public class ControllerUtilTest {

	@Test
	public void getEndDate() {
		Date expected = Date.valueOf("2015-10-10");
		Date actual = ControllerUtil.getEndDate(7, Date.valueOf("2015-10-03"));
		assertEquals(expected, actual);
	}
}